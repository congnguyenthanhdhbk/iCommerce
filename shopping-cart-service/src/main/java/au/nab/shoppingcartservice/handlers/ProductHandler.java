package au.nab.shoppingcartservice.handlers;

import au.nab.shoppingcartservice.entities.Product;
import au.nab.shoppingcartservice.handlers.etos.ProductEto;
import au.nab.shoppingcartservice.handlers.etos.topics.ProductTopic;
import au.nab.shoppingcartservice.repositories.ProductRepository;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class ProductHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProductHandler.class);
    private final ProductRepository productRepository;

    public ProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = ProductTopic.CREATED, groupId = ProductTopic.GROUP_ID)
    void listener(final String data) {
        final ProductEto product = new GsonBuilder()
                .create()
                .fromJson(data, ProductEto.class);
        final Product savedProduct = productRepository.save(Product
                .builder()
                        .brand(product.getBrand())
                        .id(product.getId())
                        .categories(product.getCategories())
                        .colors(product.getColors())
                        .name(product.getName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .createdAt(LocalDateTime.now(ZoneOffset.UTC))
                        .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
                .build());
        logger.info("Event stories: " + savedProduct.toString());
    }
}
