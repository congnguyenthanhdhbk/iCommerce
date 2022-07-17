package au.nab.shoppingcartservice.controllers;

import au.nab.shoppingcartservice.dtos.CartItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/carts")
public interface CartItemController {
    ResponseEntity<?> addCartItems(final CartItemDto cartItemDto);
    ResponseEntity<?> updateCartItems(final String cartId, final CartItemDto cartItemDto);
}
