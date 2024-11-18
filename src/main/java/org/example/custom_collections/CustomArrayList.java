package org.example.custom_collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_SIZE = 12;
    private Object[] array;
    private int size = 0;

    public CustomArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public CustomArrayList(int size) {
        if (size > 0) {
            this.size = size;
            this.array = new Object[size];
        } else {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) array[index];
    }

    public void put(E elem) {
        checkSize();
        array[size++] = elem;
    }

    public void delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int numOfElemToMove = size - index - 1;
        if (numOfElemToMove > 0) {
            System.arraycopy(array, index + 1, array, index, numOfElemToMove);
        }
        array[--size] = null;

    }


    private void checkSize() {
        if (size == array.length) {
            int newSize = array.length * 2;
            array = Arrays.copyOf(array, newSize);
        }
    }


    public int size() {
        return this.size;
    }

    // Implementing the iterator() method to allow forEach
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) array[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }
}
