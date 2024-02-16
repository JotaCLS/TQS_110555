package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private final List<T> stack;


    public TqsStack() {
        this.stack = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public boolean isFull() {
        return false;
    }


    public void push(T stack_item) {
        stack.add(stack_item);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.get(stack.size() - 1);

    }

    public int size() {
        return stack.size();

    }

}
