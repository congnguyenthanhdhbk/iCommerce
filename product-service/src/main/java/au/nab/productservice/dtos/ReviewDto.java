package au.nab.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReviewDto {
    @NotBlank(message = "reviewer must not be null or empty")
    private String reviewBy;
    private String comment;
    private List<String> images;
    @Min(value = 1, message = "star must be greater than or equals from 1")
    @Max(value = 5, message = "star must be less than or equals 5")
    private int star;
}
