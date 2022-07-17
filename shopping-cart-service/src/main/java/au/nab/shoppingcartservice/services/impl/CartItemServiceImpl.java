package au.nab.shoppingcartservice.services.impl;

import au.nab.shoppingcartservice.dtos.CartItemDto;
import au.nab.shoppingcartservice.entities.CartItem;
import au.nab.shoppingcartservice.entities.Product;
import au.nab.shoppingcartservice.exceptions.NoItemValidException;
import au.nab.shoppingcartservice.exceptions.NotFoundException;
import au.nab.shoppingcartservice.repositories.CartItemRepository;
import au.nab.shoppingcartservice.repositories.ProductRepository;
import au.nab.shoppingcartservice.services.CartItemService;
import au.nab.shoppingcartservice.services.validation.CartItemValidation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartItemValidation validation;

    public CartItemServiceImpl(final CartItemRepository cartItemRepository,
                               final ProductRepository productRepository,
                               final CartItemValidation validation) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.validation = validation;
    }

    @Override
    public boolean addItemsToCart(final CartItemDto cartItemDto) throws NoItemValidException, NotFoundException {
        // Validate and filter invalid product
        final List<String> productIds = validation.checkValidProduct(cartItemDto.getProductIds());
        // Get list product
        final List<Product> products = (List<Product>) productRepository.findAllById(productIds);
        final long quantity = productIds.size();
        int totals = 0;
        for (Product product : products) {
            totals += product.getPrice().intValue();
        }

        cartItemRepository.save(CartItem
                .builder()
                        .userId(cartItemDto.getUserId())
                        .totals(BigDecimal.valueOf(totals))
                        .quantity(quantity)
                        .productIds(productIds)
                .build());
        return true;
    }

    @Override
    public boolean updateItemsToCart(final CartItemDto cartItemDto) throws NoItemValidException, NotFoundException {
        Objects.requireNonNull(cartItemDto.getCartId(), "Cart id must not be null or empty");
        // Validate and filter invalid product
        final List<String> productIds = cartItemDto
                .getProductIds()
                .stream()
                .filter(productRepository::existsById)
                .collect(Collectors.toList());
        if (productIds.size() == 0) {
            throw new NoItemValidException("No products is valid or exist");
        }
        // Get list product
        final List<Product> products = (List<Product>) productRepository.findAllById(productIds);
        final long quantity = productIds.size();
        int totals = 0;
        for (Product product : products) {
            totals += product.getPrice().intValue();
        }

        final Optional<CartItem> cartItem = cartItemRepository
                .findById(cartItemDto.getCartId());
        if (cartItem.isEmpty()) {
            throw new NotFoundException("Cart item is not exists");
        }
        cartItem.get().setQuantity(quantity);
        cartItem.get().setProductIds(productIds);
        cartItem.get().setTotals(BigDecimal.valueOf(totals));
        cartItemRepository.save(cartItem.get());

        return true;
    }
}
