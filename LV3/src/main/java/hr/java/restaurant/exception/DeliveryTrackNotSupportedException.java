package hr.java.restaurant.exception;

public class DeliveryTrackNotSupportedException extends RuntimeException {
    public DeliveryTrackNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
