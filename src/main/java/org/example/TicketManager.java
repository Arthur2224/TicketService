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



}
