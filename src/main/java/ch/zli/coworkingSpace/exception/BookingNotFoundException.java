package ch.zli.coworkingSpace.exception;

public class BookingNotFoundException  extends RuntimeException {
    public BookingNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
