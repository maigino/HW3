/**
 * An exception specific to stack operations.
 * Extends the {@link RuntimeException} class.
 */
public class StackException extends RuntimeException {

    /**
     * Constructs a new StackException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public StackException(String message) {
        super(message);
    }

    /**
     * Constructs a new StackException with no specified detail message.
     */
    public StackException() {
    }
    public StackException(String message, Throwable cause){
        super(message,cause);
    }
}
