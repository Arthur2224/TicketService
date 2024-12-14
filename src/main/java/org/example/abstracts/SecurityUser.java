package org.example.abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Status;
import org.example.interfaces.Printable;

/**
 * Abstract class representing a security user with properties such as password, admin status, and user status.
 * This class is designed to separate security concerns from business logic.
 * It serves as a base class for Client, to handle security-related functionality.
 * The class implements the {@link Printable} interface to provide a method for printing the user's role.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SecurityUser implements Printable {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.INACTIVE;

    @Override
    public void printRole() {
        System.out.println(isAdmin ? "ADMIN" : "USER");
    }
}
