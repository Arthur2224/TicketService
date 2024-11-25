package org.example.managers;

import org.example.entities.Ticket;
import org.example.services.TicketService;

import java.util.List;
import java.util.Optional;

public class TicketManager {

    private final TicketService ticketService;

    public TicketManager(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void addTicket(Ticket ticket) {
        ticketService.addTicket(ticket);
        System.out.println("Added ticket: " + ticket);
    }

    public void deleteTicketById(long id) {
        List<Ticket> tickets = ticketService.getAllTickets();
        Ticket ticketToDelete = findTicketById(tickets, id);
        if (ticketToDelete != null) {
            ticketService.deleteTicketById(ticketToDelete);
            System.out.println("Deleted ticket by ID: " + id);
        } else {
            System.out.println("No ticket found by ID: " + id);
        }
    }

    public void updateTicket(Long ticketId, Ticket updatedTicket) {
        Optional<Ticket> optionalTicket = ticketService.getTicketById(ticketId);
        optionalTicket.ifPresentOrElse(
                ticket -> {
                    ticketService.updateTicket(ticket, updatedTicket);
                    System.out.println("Updated ticket ooncert hall to: " + updatedTicket.getConcertHall() + " and event code to: " + updatedTicket.getEventCode());
                },
                () -> System.out.println("No ticket found with ID: " + ticketId)
        );
    }

    public void printAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            tickets.forEach(ticket -> System.out.println(ticket));
        }
        System.out.println("\n");
    }

    private Ticket findTicketById(List<Ticket> tickets, long id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
