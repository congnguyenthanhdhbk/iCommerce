package au.nab.productservice.controller;

import au.nab.productservice.dtos.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.Product;
import au.nab.productservice.entities.Review;
import au.nab.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/products")
public interface ProductController {
    @PostMapping()
    ResponseEntity<BaseResponseDto<Product>> addProduct(final ProductDto product);
    @PutMapping("{id}/reviews")
    ResponseEntity<BaseResponseDto<Product>> reviewProduct(@RequestBody() final ReviewDto review,
                                                           @PathVariable("id") final String id);
}
