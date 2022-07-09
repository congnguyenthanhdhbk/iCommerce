package au.nab.productservice.service.impl;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import au.nab.productservice.repository.ProductRepository;
import au.nab.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductResponse> addProduct(final ProductDto product) {
        final Product entity = product.toEntity();
        final Product savedProduct = productRepository.insert(entity);
        return Optional.of(new ProductResponse(savedProduct));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAll(final Query query) {
        return productRepository.findAll(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Product> getPage(final Query query, final Pageable pageable) {
        return productRepository.findAll(query, pageable);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> updateProduct(String id, final ProductDto productDto) {
        final Product product = productDto.toEntity();
        product.setId(id);
        return Optional.of(productRepository.save(product));
    }
}
