package hr.java.restaurant.exception;

// Unchecked exception
public class BogusEntryException extends RuntimeException {
    public BogusEntryException(String message) {
        super(message);
    }
}
