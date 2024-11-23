package org.example;

import org.example.DAOs.ClientDAO;
import org.example.entities.Client;
import org.example.managers.ClientManager;
import org.example.services.ClientService;

public class TicketService {
    public static void main(String[] args) {

        ClientService clientService = new ClientService(new ClientDAO());
        ClientManager clientManager = new ClientManager(clientService);

        System.out.println("Current clients:");
        clientManager.printAllClients();

        Client testClient = new Client("Arthur", "arthur1@gmail.com");

        // Delete by email client if exists
        clientManager.deleteClientByEmail(testClient.getEmail());
        // Delete by id
        clientManager.deleteClientById(2L);

        // Insert new client to DB
        clientManager.addClient(testClient);

        System.out.println("Clients after adding new client:");
        clientManager.printAllClients();

        // Update the clients email
        clientManager.updateClientEmail(1L, "arthur0@gmail.com");

        System.out.println("Clients after updating clients email:");
        clientManager.printAllClients();
    }
}
