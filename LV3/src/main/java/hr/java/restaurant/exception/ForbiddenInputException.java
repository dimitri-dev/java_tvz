package hr.java.restaurant.exception;

public class ForbiddenInputException extends RuntimeException {
    private String entityName;
    private String message;
    private String fieldName;

    public ForbiddenInputException(String message, String entityName, String fieldName) {
        super(message);

        setMessage(message);
        setEntityName(entityName);
        setFieldName(fieldName);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
