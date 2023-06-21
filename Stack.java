/**
 * A generic stack data structure.
 *
 * @param <E> the type of elements stored in the stack, must implement Cloneable
 */
public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    void push(E element);
    /**
     * Pops and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    E pop();
    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    E peek();
    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    int size();
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();
    /**
     * Creates a shallow copy of the stack.
     *
     * @return a cloned instance of the stack
     */
    Stack<E> clone();

}


