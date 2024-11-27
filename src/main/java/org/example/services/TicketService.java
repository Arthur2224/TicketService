package org.example.services;

import org.example.DAOs.TicketDAO;
import org.example.entities.Ticket;

import java.util.List;

public class TicketService {
    private final TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void addTicket(Ticket ticket) {
        ticketDAO.save(ticket);
        System.out.println("Added new ticket with id: " + ticket.getId());
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketDAO.getAll();
        System.out.println("Fetched all tickets: " + tickets.size() + " ticket(s) found.");
        return tickets;
    }

    public Ticket getTicketById(Long id) {
        Ticket ticket = ticketDAO.findById(id);
        if (ticket != null) {
            System.out.println("Found ticket by ID: " + id);
        } else {
            System.out.println("No ticket found with ID: " + id);
        }
        return ticket;
    }

    public void updateTicket(Long id, Ticket ticketDetails) {
        ticketDAO.update(id, ticketDetails);
        System.out.println("Updating ticket with ID: " + id);
    }

    public void delete(long id) {
        ticketDAO.delete(id);
        System.out.println("Removing ticket with ID: " + id);
    }

    public void printAllTickets() {
        List<Ticket> tickets = getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            tickets.forEach(ticket -> System.out.println(ticket));
        }
        System.out.println("\n");
    }
}
