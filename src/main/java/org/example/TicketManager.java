package org.example;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private List<Ticket> tickets;

    public TicketManager() {
        tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void printAllTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public Ticket findTicketById(String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }


}
