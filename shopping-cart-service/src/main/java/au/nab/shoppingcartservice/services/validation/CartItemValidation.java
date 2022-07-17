package au.nab.shoppingcartservice.services.validation;

import au.nab.shoppingcartservice.exceptions.NoItemValidException;
import au.nab.shoppingcartservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemValidation {
    private final ProductRepository productRepository;

    public CartItemValidation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> checkValidProduct(final List<String> items) throws NoItemValidException {
        List<String> validProduct = items.stream().filter(productRepository::existsById).collect(Collectors.toList());
        if (validProduct.isEmpty()) {
            throw new NoItemValidException("No products is valid or exist");
        }
        return validProduct;
    }
}
