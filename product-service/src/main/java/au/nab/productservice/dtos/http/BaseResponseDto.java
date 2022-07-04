package au.nab.productservice.dtos.http;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BaseResponseDto {
    private int code;
    private String message;
    private ProductResponse data;
}
