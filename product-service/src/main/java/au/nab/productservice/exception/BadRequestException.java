package au.nab.productservice.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(final String msg, Throwable t) {
        super(msg, t);
    }

    public BadRequestException(final String msg) {
        super(msg);
    }
}
