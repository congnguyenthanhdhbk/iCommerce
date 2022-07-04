package au.nab.productservice.repository;

import au.nab.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends MongoRepository<Product, String>,
        PagingAndSortingRepository<Product, String> {
}
