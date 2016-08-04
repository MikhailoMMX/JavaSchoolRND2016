package ru.sbrf.javacourse.buildtools;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayIterator<E> implements Iterator<E> {

    private final E[] elements;

    private int index = 0;

    public ArrayIterator(E[] elements) {
        this.elements = elements;
    }

    public boolean hasNext() {
        return index < elements.length;
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return elements[index++];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}

public class Application {

    public static void main(String[] args) {
        Integer[] e = {1, 2, 3, 4, 5};

        ArrayIterator<Integer> it = new ArrayIterator<>(e);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
