package au.nab.shoppingcartservice.repositories;

import au.nab.shoppingcartservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
