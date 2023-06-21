/**
 * An exception indicating that the stack is empty.
 * Extends the {@link StackException} class.
 */
public class EmptyStackException extends StackException {

    /**
     * Constructs a new EmptyStackException with a default error message.
     * The error message indicates that the stack is empty.
     */
    public EmptyStackException() {
        super("The stack is empty.");
    }
}
