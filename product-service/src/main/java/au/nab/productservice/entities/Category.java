package au.nab.productservice.entities;

import au.nab.productservice.constants.Collection;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Category {
    private String name;
    private String description;
}
