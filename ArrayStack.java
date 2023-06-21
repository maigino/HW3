import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
/**
 * Represents a stack implemented using an array.
 *
 * @param <E> the type of elements stored in the stack, must implement Cloneable
 */
public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E> {
    private Object[] array;
    private int top;
    private final int capacity;
    /**
     * Constructs an ArrayStack with the specified capacity.
     *
     * @param capacity the maximum number of elements the stack can hold
     * @throws NegativeCapacityException if the specified capacity is negative
     */
    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new NegativeCapacityException();
        }
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.top = -1;
    }
    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     * @throws StackOverflowException if the stack is already full
     */
    @Override
    public void push(E element) {
        if (isFull()) {
            throw new StackOverflowException();
        }
        top++;
        array[top] = element;
    }
    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = (E) array[top];
        array[top] = null;
        top--;
        return element;
    }
    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = (E) array[top];
        return element;
    }
    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */

    @Override
    public int size() {
        return top + 1;
    }
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
    /**
     * Checks if the stack is full and cannot accept more elements.
     *
     * @return true if the stack is full, false otherwise
     */
    private boolean isFull() {
        return top == capacity - 1;
    }
    /**
     * Creates a shallow copy of the stack.
     *
     * @return a cloned instance of the stack
     */
    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> clonedStack = (ArrayStack<E>) super.clone();
            clonedStack.array = array.clone();

            for (int i = 0; i <= top; i++) {
                try {
                    clonedStack.array[i] = array[i].getClass().getDeclaredMethod("clone").invoke(array[i]);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }

            return clonedStack;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator<E>();
    }
    /**
     * Iterator implementation for ArrayStack.
     */
    private class StackIterator<E> implements Iterator<E> {
        private int currentIndex = top;
        /**
         * Checks if there is a next element in the iteration.
         *
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }
        /**
         * Returns the next element in the iteration.
         *
         * @return the next element
         */
        @Override
        public E next() {
            E element = (E) array[currentIndex];
            currentIndex--;
            return element;
        }
    }
}