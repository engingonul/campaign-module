package validator;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(String errorMessage) {
        super(errorMessage);
    }
}