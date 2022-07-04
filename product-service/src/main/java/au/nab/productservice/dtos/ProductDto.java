package au.nab.productservice.dtos;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "product name must not be null or empty")
    private final String name;
    @NotBlank(message = "product description must not be null or empty")
    @Max(value = 1000, message = "product description must not be greater than 300 characters")
    private final String description;
    @NotNull(message = "colors must not be null or empty")
    private final List<String> colors;
    @NotNull(message = "category must not be null or empty")
    private final List<String> categories;
    @NotNull(message = "brand must not be null or empty")
    private final String brand;
    @NotBlank(message = "quantity must not be null or empty")
    private final long quantity;
    @NotBlank(message = "price must not be null or empty")
    private final BigDecimal price;
}
