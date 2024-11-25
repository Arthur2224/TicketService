package org.example;

import org.example.DAOs.ClientDAO;
import org.example.DAOs.TicketDAO;
import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.example.managers.ClientManager;
import org.example.managers.TicketManager;
import org.example.services.ClientService;
import org.example.services.TicketService;

import java.math.BigDecimal;

public class TicketServiceApp {
    public static void main(String[] args) {

        ClientService clientService = new ClientService(new ClientDAO());
        ClientManager clientManager = new ClientManager(clientService);

        TicketService ticketService= new TicketService(new TicketDAO());
        TicketManager ticketManager = new TicketManager(ticketService);

        System.out.println("Current clients:");
        clientManager.printAllClients();
        Client testClient = new Client("Arthur", "arthur1@gmail.com");

        // Delete by id
        clientManager.deleteClientById(10L);

        // Delete by email client if exists
        clientManager.deleteClientByEmail(testClient.getEmail());
        System.out.println("Clients after delete by id and email:");
        clientManager.printAllClients();

        // Insert new client to DB
        clientManager.addClient(testClient);

        System.out.println("Clients after adding new client:");
        clientManager.printAllClients();

        // Update the clients email
        clientManager.updateClientEmail(1L, "arthur0@gmail.com");

        System.out.println("Clients after updating clients email:");
        clientManager.printAllClients();

        System.out.println("Current tickets:");
        ticketManager.printAllTickets();

        Ticket testTicket = new Ticket(
                "Hall B",
                124,
                true,
                StadiumSectors.C,
                10.0,
                BigDecimal.valueOf(21.5),
                clientManager.findClientById(3L)
        );

        // Add new ticket to DB
        ticketManager.addTicket(testTicket);

        // Print all tickets after adding new ticket
        System.out.println("Tickets after adding new ticket:");
        ticketManager.printAllTickets();

        // Update ticket's concert hall
        testTicket.setEventCode(125);
        ticketManager.updateTicket(testTicket.getId(),testTicket );

        // Print all tickets after updating
        System.out.println("Tickets after updating ticket:");
        ticketManager.printAllTickets();

        // Delete ticket by ID
        ticketManager.deleteTicketById(testTicket.getId());

        // Print all tickets after deleting
        System.out.println("Tickets after deleting ticket:");
        ticketManager.printAllTickets();
    }
}
