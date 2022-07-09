package au.nab.productservice.service;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(final ProductDto product);

    /**
     * @param query custom query
     * @return list of Product
     */
    List<Product> getAll(final Query query);
    /**
     * Get all custom paginate data for entity Product
     *
     * @param query    custom query
     * @param pageable pageable param
     * @return Page of entity Product
     */
    Page<Product> getPage(final Query query, final Pageable pageable);
}
