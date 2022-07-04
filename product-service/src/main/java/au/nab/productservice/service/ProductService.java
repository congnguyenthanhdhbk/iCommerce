package au.nab.productservice.service;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse addProduct(final ProductDto product);
}
