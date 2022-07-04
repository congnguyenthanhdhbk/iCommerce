package au.nab.productservice.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private final String id;
    private final String name;
    private final String description;
    private final List<String> colors;
    private final List<String> categories;
    private final String brand;
    private final long quantity;
    private final BigDecimal price;
}
