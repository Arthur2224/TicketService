package org.example.services;

import org.example.DAOs.TicketDAO;
import org.example.entities.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketService {
    TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void addTicket(Ticket ticket) {
        ticketDAO.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.getAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketDAO.findById(id);
    }

    public void updateTicket(Ticket ticket, Ticket updatedTicket) {
        ticketDAO.update(ticket, updatedTicket);
    }

    public void deleteTicketById(Ticket ticket) {
        ticketDAO.delete(ticket);
    }
}
