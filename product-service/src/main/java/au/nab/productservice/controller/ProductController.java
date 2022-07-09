package au.nab.productservice.controller;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.PageResponse;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/v1/products")
public interface ProductController {
    @PostMapping()
    ResponseEntity<ProductResponse> addProduct(final ProductDto product);
    @GetMapping()
    ResponseEntity<PageResponse<Product>> getSearchCriteriaPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders);
    @GetMapping("/list")
    ResponseEntity<List<Product>> getAllSearchCriteria(@RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd);
    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("id") final String id);
    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") final String id, @RequestBody() final ProductDto product);
}
