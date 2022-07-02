package au.nab.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@Getter
public class InventoryDto {
    @NotBlank(message = "inventory quantity must not be null or empty")
    private final long quantity;
    @NotBlank(message = "inventory price must not be null or empty")
    private final BigDecimal price;
}
