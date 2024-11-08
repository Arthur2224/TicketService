package org.example.entities;

public class Admin extends User{
    @Override
    public void printRole(){
        System.out.println("ROLE: ADMIN");
    }
    public void checkTicket(){
        System.out.println("Ticket has been checked");
    }
}
