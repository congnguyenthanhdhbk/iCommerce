package au.nab.shoppingcartservice.services;

import au.nab.shoppingcartservice.dtos.CartItemDto;
import au.nab.shoppingcartservice.exceptions.NoItemValidException;
import au.nab.shoppingcartservice.exceptions.NotFoundException;

public interface CartItemService {
    boolean addItemsToCart(final CartItemDto cartItemDto) throws NoItemValidException, NotFoundException;
    boolean updateItemsToCart(final CartItemDto cartItemDto) throws NoItemValidException, NotFoundException;
}
