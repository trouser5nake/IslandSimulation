package exceptions;

public class NoSuchAnnotationException extends RuntimeException {
    public NoSuchAnnotationException(String message) {
        super(message);
    }
}