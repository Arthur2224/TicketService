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
        Client anotherTestClient = new Client("Tim", "tim@gmail.com");
        clientService.addClient(testClient);
        clientService.addClient(anotherTestClient);
        System.out.println("Clients after adding new client:");
        clientService.printAllClients();

        clientService.deleteClient(2L);
        System.out.println("Clients after removing by id:");
        clientService.printAllClients();

        testClient.setName("name also has been updated");
        testClient.setEmail("updated email");
        clientService.updateClient(testClient);

        System.out.println("Clients after updating clients email:");
        clientService.printAllClients();

        System.out.println("Current tickets:");
        ticketService.printAllTickets();

        Ticket testTicket = new Ticket("Match A", 101, true, StadiumSectors.C, 22.5, BigDecimal.valueOf(50.0), clientService.getClientById(1L));
        ticketService.addTicket(testTicket);
        System.out.println("Tickets after adding new ticket:");
        ticketService.printAllTickets();

        testTicket.setConcertHall("New");
        testTicket.setPrice(BigDecimal.valueOf(75.0));
        ticketService.updateTicket(testTicket);
        System.out.println( ticketService.getTicketById(3L));

        ticketService.delete(testTicket.getId());
        System.out.println("Tickets after removing by id:");
        ticketService.printAllTickets();

        System.out.println("Updating all tickets of certain client:");
        Client testTicketsAndClientUpdate=new Client("Client with Tickets", "tickets@gmail.com");

        clientService.addClient(testTicketsAndClientUpdate);
        ticketService.addTicket(new Ticket("Match A", 101, true, StadiumSectors.A, 22.5, BigDecimal.valueOf(50.0), testTicketsAndClientUpdate));
        ticketService.addTicket(new Ticket("Match B", 102, true, StadiumSectors.B, 23.5, BigDecimal.valueOf(1729.0), testTicketsAndClientUpdate));
        ticketService.addTicket(new Ticket("Match C", 101, true, StadiumSectors.C, 212.5, BigDecimal.valueOf(5.0), testTicketsAndClientUpdate));
        ticketService.addTicket(new Ticket("Match A", 103, false, StadiumSectors.C, 1.5, BigDecimal.valueOf(43.56), testTicketsAndClientUpdate));

        clientService.getClientById(testTicketsAndClientUpdate.getId()).getTickets().stream().forEach(ticket -> System.out.println(ticket));

        testTicketsAndClientUpdate= clientService.getClientById(testTicketsAndClientUpdate.getId());
        testTicketsAndClientUpdate.getTickets().stream().forEach(ticket -> ticket.setPrice(BigDecimal.valueOf(1.11)));
        clientService.updateClient(testTicketsAndClientUpdate);
        clientService.getClientById(testTicketsAndClientUpdate.getId()).getTickets().stream().forEach(ticket -> System.out.println(ticket));
    }
}
