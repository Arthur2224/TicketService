package org.example.entities;

public class Client extends User {
    @Override
    public void printRole() {
        System.out.println("Role: CLIENT");
    }

    public void getTicket() {
        System.out.println("Client getting ticket.");
    }
}
