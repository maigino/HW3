/**
 * An exception indicating that the stack has reached its full capacity.
 * Extends the {@link StackException} class.
 */
public class StackOverflowException extends StackException {

    /**
     * Constructs a new StackOverflowException with a default error message.
     * The error message indicates that the stack has reached its full capacity.
     */
    public StackOverflowException() {
        super("The stack reached its full capacity.");
    }
}
