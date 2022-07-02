package au.nab.productservice.entities;

import au.nab.productservice.constants.Collection;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Review {
    private String reviewBy;
    private String comment;
    private List<String> images;
    private int start;
}
