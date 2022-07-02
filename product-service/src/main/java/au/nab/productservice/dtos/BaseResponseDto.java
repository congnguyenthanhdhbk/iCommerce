package au.nab.productservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BaseResponseDto<T> {
    private int code;
    private String message;
    private T data;
}
