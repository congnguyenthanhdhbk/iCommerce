package au.nab.shoppingcartservice.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CartItemDto {
    private final String cartId;
    @NotBlank(message = "product ids must not be null or empty")
    private final List<String> productIds;
    private final String userId;
}
