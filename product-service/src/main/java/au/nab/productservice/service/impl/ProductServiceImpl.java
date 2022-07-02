package au.nab.productservice.service.impl;

import au.nab.productservice.constants.User;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.*;
import au.nab.productservice.repository.ProductRepository;
import au.nab.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(final ProductDto product) {
        final Brand brand = Brand
                .builder()
                .name(product.getBrand().getName())
                .build();
        final List<Category> categories = product
                .getCategories()
                .stream()
                .map((c) -> Category
                            .builder()
                            .name(c.getName())
                            .description(c.getDescription())
                            .build()).collect(Collectors.toList());
        final Inventory inventory = Inventory
                .builder()
                .price(product.getInventory().getPrice())
                .quantity(product.getInventory().getQuantity())
                .build();
        final Product productEntity = Product
                .builder()
                .brand(brand)
                .categories(categories)
                .inventory(inventory)
                .color(product.getColors())
                .sku(product.getSku())
                .createdAt(LocalDateTime.now(ZoneOffset.UTC))
                .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
                .createdBy(User.OPERATOR)
                .updatedBy(User.OPERATOR)
                .description(product.getDescription())
                .review(new ArrayList<>())
                .name(product.getName())
                .build();
        return productRepository.insert(productEntity);
    }

    @Override
    public Product reviewProduct(final ReviewDto reviewDto, final String id) throws Exception {
        final Optional<Product> entity = productRepository.findById(id);
        if (entity.isPresent()) {
            Product update = entity.get();
            Review review = Review.builder()
                    .reviewBy(reviewDto.getReviewBy())
                    .comment(reviewDto.getComment())
                    .images(reviewDto.getImages())
                    .start(reviewDto.getStar())
                    .build();
            update.getReview().add(review);
            update.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
            return productRepository.save(update);
        }
        throw new Exception("product is not found");
    }
}
