package au.nab.shoppingcartservice.entities;

import au.nab.shoppingcartservice.entities.constants.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Document(collection = "cartItems")
@AllArgsConstructor
@Getter@Setter@Data
@Builder
@NoArgsConstructor
public class CartItem {
    @Id
    private String id;
    private List<String> productIds;
    private BigDecimal totals;
    private String userId;
    private long quantity;
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    private String createdBy = User.USER;
    private String updatedBy = User.USER;
}
