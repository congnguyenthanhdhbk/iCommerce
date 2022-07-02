package au.nab.productservice.dtos;

import au.nab.productservice.entities.Brand;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Data
public class ProductDto {
    @NotBlank(message = "product name must not be null or empty")
    private final String name;
    @NotBlank(message = "product description must not be null or empty")
    @Max(value = 300, message = "product description must not be greater than 300 characters")
    private final String description;
    @NotBlank(message = "product sku must not be null or empty")
    private final String sku;
    @NotNull(message = "product color must not be null or empty")
    private final List<String> colors;
    @NotNull(message = "product category must not be null or empty")
    private final List<CategoryDto> categories;
    @NotNull(message = "product inventory must not be null or empty")
    private final InventoryDto inventory;
    private final ReviewDto review;
    private final BrandDto brand;
}
