package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.DAOs.TicketDAO;
import org.example.entities.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    private final TicketDAO ticketDAO;
    private ApplicationContext context;

    @Autowired
    public TicketService(TicketDAO ticketDAO, ApplicationContext context) {
        this.ticketDAO = ticketDAO;
        this.context = context;
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
    public void updateTicket(Ticket ticketDetails) {
        ticketDAO.update(ticketDetails);
        logger.info("Updated ticket with ID: {}", ticketDetails.getId());
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

    public List<Ticket> loadTickets() {
        List<Ticket> tickets = null;
        try {
            Resource resource = context.getResource("classpath:tickets.json");
            // Initialization of ObjectMapper and register the JavaTimeModule to handle LocalDateTime
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            tickets = objectMapper.readValue(resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Ticket.class));
        } catch (IOException e) {
            System.err.println("Error reading or parsing the tickets.json file: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }
}
