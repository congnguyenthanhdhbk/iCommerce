package au.nab.productservice.service.impl;

import au.nab.productservice.converters.ProductConverter;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import au.nab.productservice.repository.ProductRepository;
import au.nab.productservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public ProductResponse addProduct(final ProductDto product) {
        final Product entity = productConverter.convertToEntity(product);
        final Product savedProduct = productRepository.insert(entity);
        return productConverter.convertToDto(savedProduct);
    }
}
