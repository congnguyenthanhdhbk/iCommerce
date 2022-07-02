package au.nab.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
@Data
public class CategoryDto {
    private final String id;
    @NotBlank(message = "category must not be null or empty")
    private final String name;
    @Max(value = 300, message = "category description must not be grater than 300 characters")
    private final String description;
}
