package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@Builder
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "email")
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        onCreate();
    }

    public void onCreate() {
        createdAt = LocalDateTime.now();

    }

    public void onUpdate() {
        updatedAt = LocalDateTime.now();

    }

    @Override
    public void printRole() {
        System.out.println("Role: CLIENT");
    }

    public void getTicket() {
        System.out.println("Client getting ticket.");
    }
}
