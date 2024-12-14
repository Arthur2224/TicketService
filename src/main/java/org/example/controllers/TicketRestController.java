package org.example.controllers;

import org.example.entities.Ticket;
import org.example.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing tickets.
 * Provides endpoints for CRUD operations related to tickets.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Endpoint to get all tickets.
     *
     * @return a list of all tickets
     */
    @GetMapping("/getAll")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Endpoint to get all tickets for a specific client.
     *
     * @param clientId The ID of the client
     * @return a list of tickets associated with the client
     */
    @GetMapping("/getAllTickets/{clientId}")
    public List<Ticket> getAllClientTickets(@PathVariable Long clientId) {
        return ticketService.getAllClientsTickets(clientId);
    }

    /**
     * Endpoint to save a ticket. If the ticket does not have an ID, it will be created.
     *
     * @param ticket The ticket to be saved
     * @return the saved ticket
     */
    @PostMapping
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId();  // Ensure the ticket has an ID before saving
        }
        return ticketService.saveTicket(ticket);
    }

    /**
     * Endpoint to delete a ticket by its ID.
     *
     * @param id The ID of the ticket to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
