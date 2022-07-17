package au.nab.shoppingcartservice.controllers.impl;

import au.nab.shoppingcartservice.controllers.CartItemController;
import au.nab.shoppingcartservice.dtos.CartItemDto;
import au.nab.shoppingcartservice.services.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartItemControllerImpl implements CartItemController {
    private final CartItemService cartItemService;

    public CartItemControllerImpl(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addCartItems(@RequestBody CartItemDto cartItemDto) {
        try {
            ResponseEntity.ok(cartItemService.addItemsToCart(cartItemDto));
        } catch (Exception e) {
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @Override
    @PutMapping(value = "/{cartId}")
    public ResponseEntity<?> updateCartItems(@PathVariable(value = "cartId") final String cartId,
                                             @RequestBody final CartItemDto cartItemDto) {
        try {
            final CartItemDto cart = CartItemDto
                    .builder()
                    .cartId(cartId)
                    .productIds(cartItemDto.getProductIds())
                    .build();
            ResponseEntity.ok(cartItemService.updateItemsToCart(cartItemDto));
        } catch (Exception e) {
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
