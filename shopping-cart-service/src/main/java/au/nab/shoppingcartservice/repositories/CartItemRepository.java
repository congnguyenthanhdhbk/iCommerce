package au.nab.shoppingcartservice.repositories;

import au.nab.shoppingcartservice.entities.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
}
