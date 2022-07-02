package au.nab.productservice.entities;

import au.nab.productservice.constants.Collection;
import au.nab.productservice.constants.User;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

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
    String id;
    private String name;
    private String description;
    private String sku;
    private List<String> color;

    private Brand brand;

    private List<Category> categories;
    private Inventory inventory;
    private List<Review> review;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    @CreatedBy
    private String createdBy = User.OPERATOR;
    @LastModifiedBy
    private String updatedBy = User.OPERATOR;
    private LocalDateTime deletedAt;
}
