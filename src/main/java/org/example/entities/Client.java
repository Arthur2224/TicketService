package org.example.entities;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client extends User {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
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
}
