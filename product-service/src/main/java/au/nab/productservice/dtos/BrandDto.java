package au.nab.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
@Data
public class BrandDto {
    private final String id;
    @NotNull(message = "brand name must not be null or empty")
    private final String name;
}
