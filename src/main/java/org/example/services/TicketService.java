package org.example.services;

import org.example.DAOs.TicketDAO;
import org.example.entities.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    private final TicketDAO ticketDAO;

    @Autowired
    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Transactional
    public void addTicket(Ticket ticket) {
        ticketDAO.save(ticket);
        logger.info("Added new ticket with ID: {}", ticket.getId());
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketDAO.getAll();
        logger.info("Fetched all tickets: {} ticket(s) found.", tickets.size());
        return tickets;
    }

    public Ticket getTicketById(Long id) {
        Ticket ticket = ticketDAO.findById(id);
        if (ticket != null) {
            logger.info("Found ticket by ID: {}", id);
        } else {
            logger.warn("No ticket found with ID: {}", id);
        }
        return ticket;
    }

    @Transactional
    public void updateTicket(Long id, Ticket ticketDetails) {
        ticketDAO.update(id, ticketDetails);
        logger.info("Updated ticket with ID: {}", id);
    }

    @Transactional
    public void delete(long id) {
        ticketDAO.delete(id);
        logger.info("Removed ticket with ID: {}", id);
    }

    public void printAllTickets() {
        List<Ticket> tickets = getAllTickets();
        if (tickets.isEmpty()) {
            logger.info("No tickets found.");
        } else {
            tickets.forEach(ticket -> logger.info("Ticket: {}", ticket));
        }
    }
}
