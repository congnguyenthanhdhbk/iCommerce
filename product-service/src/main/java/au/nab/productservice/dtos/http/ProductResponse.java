package au.nab.productservice.dtos.http;

import au.nab.productservice.entities.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private final String id;
    private final String name;
    private final String description;
    private final List<String> colors;
    private final List<String> categories;
    private final String brand;
    private final long quantity;
    private final BigDecimal price;

    public ProductResponse(final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.colors = product.getColors();
        this.categories = product.getCategories();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }
}
