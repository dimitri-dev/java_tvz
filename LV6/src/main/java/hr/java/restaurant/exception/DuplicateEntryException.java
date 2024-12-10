package hr.java.restaurant.exception;

// Checked exception - anticipated
public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String message) {
      super(message);
    }

    public DuplicateEntryException(String message, Throwable cause) {
      super(message, cause);
    }

    public DuplicateEntryException(Throwable cause) {
      super(cause);
    }
}
