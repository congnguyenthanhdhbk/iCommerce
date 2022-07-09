package au.nab.productservice.controller;

import au.nab.productservice.dtos.http.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.PageResponse;
import au.nab.productservice.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/v1/products")
public interface ProductController {
    @PostMapping()
    ResponseEntity<BaseResponseDto> addProduct(final ProductDto product);
    @GetMapping("/page")
    ResponseEntity<PageResponse<Product>> getSearchCriteriaPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders);
    @GetMapping
    public ResponseEntity<List<Product>> getAllSearchCriteria(
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd);
}
