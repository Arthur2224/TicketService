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
        Client testClient = new Client("Arthur", "arthur23@gmail.com");

        clientManager.deleteClientById(10L);

        clientManager.addClient(testClient);

        System.out.println("Clients after adding new client:");
        clientManager.printAllClients();

        clientManager.deleteClientByEmail("sofia.rossi@example.com");
        System.out.println("Clients after delete by id and email:");
        clientManager.printAllClients();

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
        ticketManager.addTicket(testTicket);
        System.out.println("Tickets after adding new ticket:");
        ticketManager.printAllTickets();

        testTicket.setEventCode(125);
        ticketManager.updateTicket(testTicket.getId(),testTicket );
        System.out.println("Tickets after updating ticket:");
        ticketManager.printAllTickets();

        ticketManager.deleteTicketById(testTicket.getId());
        System.out.println("Tickets after deleting ticket:");
        ticketManager.printAllTickets();
    }
}
