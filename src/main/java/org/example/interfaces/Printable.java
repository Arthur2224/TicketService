package org.example.interfaces;

public interface Printable {
    default void print() {
        System.out.println(this);
    }
}
