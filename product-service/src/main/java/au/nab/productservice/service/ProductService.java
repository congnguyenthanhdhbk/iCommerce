package au.nab.productservice.service;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.Product;

public interface ProductService {
    Product addProduct(final ProductDto product);
    Product reviewProduct(final ReviewDto review, final String id) throws Exception;
}
