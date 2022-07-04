package au.nab.productservice.controller;

import au.nab.productservice.dtos.http.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/products")
public interface ProductController {
    @PostMapping()
    ResponseEntity<BaseResponseDto> addProduct(final ProductDto product);
}
