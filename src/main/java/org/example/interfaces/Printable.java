package org.example.interfaces;

public interface Printable {
    default void printRole() {
        System.out.println(this);
    }
}
