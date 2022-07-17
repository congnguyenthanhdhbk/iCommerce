package au.nab.shoppingcartservice.exceptions;

public class NoItemValidException extends Exception {
    public NoItemValidException(final String message) {
        super(message);
    }
}