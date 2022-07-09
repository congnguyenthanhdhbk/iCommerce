package au.nab.productservice.repository;

import au.nab.productservice.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ResourceRepository<Product, String> {
}
