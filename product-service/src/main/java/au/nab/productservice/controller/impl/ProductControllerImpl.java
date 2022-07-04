package au.nab.productservice.controller.impl;

import au.nab.productservice.controller.ProductController;
import au.nab.productservice.dtos.http.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import au.nab.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<BaseResponseDto> addProduct(@RequestBody() ProductDto product) {
        try {
            final ProductResponse response = productService.addProduct(product);
            return ResponseEntity.ok(BaseResponseDto
                    .builder()
                            .code(HttpStatus.CREATED.value())
                            .message(HttpStatus.CREATED.getReasonPhrase())
                            .data(response)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of(BaseResponseDto
                    .<Product>builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(e.getMessage())
                            .data(null)
                    .build()));
        }
    }
}
