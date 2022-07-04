package au.nab.productservice.entities;

import au.nab.productservice.constants.Collection;
import au.nab.productservice.constants.User;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Document(collection = Collection.PRODUCT)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> colors;
    private String brand;
    private List<String> categories;
    private long quantity;
    private BigDecimal price;

    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    private String createdBy = User.OPERATOR;
    private String updatedBy = User.OPERATOR;
    private LocalDateTime deletedAt;
}
