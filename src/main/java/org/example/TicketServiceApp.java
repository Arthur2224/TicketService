package org.example;

import org.example.DAOs.ClientDAO;
import org.example.DAOs.TicketDAO;
import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.example.services.ClientService;
import org.example.services.TicketService;

import java.math.BigDecimal;

public class TicketServiceApp {
    public static void main(String[] args) {
        ClientService clientService = new ClientService(new ClientDAO());
        TicketService ticketService = new TicketService(new TicketDAO());

        System.out.println("Current clients:");
        clientService.printAllClients();

        Client testClient = new Client("Arthur", "arthur1332@gmail.com");
        clientService.addClient(testClient);
        System.out.println("Clients after adding new client:");
        clientService.printAllClients();

        clientService.deleteClient(14L);
        System.out.println("Clients after removing by id:");
        clientService.printAllClients();

        testClient.setName("name also has been updated");
        testClient.setEmail("updated email");
        clientService.updateClient(10L, testClient);

        System.out.println("Clients after updating clients email:");
        clientService.printAllClients();

        System.out.println("Current tickets:");
        ticketService.printAllTickets();

        Ticket testTicket = new Ticket("Match A", 101, true, StadiumSectors.C, 22.5, BigDecimal.valueOf(50.0), clientService.getClientById(15L));
        ticketService.addTicket(testTicket);
        System.out.println("Tickets after adding new ticket:");
        ticketService.printAllTickets();

        ticketService.delete(12L);
        System.out.println("Tickets after removing by id:");
        ticketService.printAllTickets();

        testTicket.setConcertHall("New");
        testTicket.setPrice(BigDecimal.valueOf(75.0));
        ticketService.updateTicket(13L, testTicket);
        ticketService.getTicketById(13L).print();

    }
}
