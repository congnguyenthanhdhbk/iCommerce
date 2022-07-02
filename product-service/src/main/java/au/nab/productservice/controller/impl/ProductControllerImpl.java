package au.nab.productservice.controller.impl;

import au.nab.productservice.controller.ProductController;
import au.nab.productservice.dtos.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.Product;
import au.nab.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<BaseResponseDto<Product>> addProduct(@RequestBody() ProductDto product) {
        try {
            return ResponseEntity.ok(BaseResponseDto
                    .<Product>builder()
                            .code(HttpStatus.CREATED.value())
                            .message(HttpStatus.CREATED.getReasonPhrase())
                            .data(productService.addProduct(product))
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

    @Override
    public ResponseEntity<BaseResponseDto<Product>> reviewProduct(@RequestBody() ReviewDto review,
                                                                  @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(BaseResponseDto.<Product>builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(productService.reviewProduct(review, id))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of(
                    BaseResponseDto.<Product>builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .data(null)
                            .message(e.getMessage())
                            .build()
            ));
        }
    }
}
