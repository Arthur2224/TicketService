package org.example.services;

import org.example.DAOs.ClientDAO;
import org.example.entities.Client;

import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void addClient(Client client) {
        clientDAO.save(client);
        System.out.println("Added client: " + client.getName() + " (" + client.getEmail() + ")");
    }

    public List<Client> getAllClients() {
        List<Client> clients = clientDAO.getAll();
        System.out.println("Fetched all clients: " + clients.size() + " client(s) found.");
        return clients;
    }

    public Client getClientById(Long id) {
        Client client = clientDAO.findById(id);
        if (client != null) {
            System.out.println("Found client by ID: " + id + " - " + client.getName());
        } else {
            System.out.println("No client found with ID: " + id);
        }
        return client;
    }

    public void updateClient(Long id, Client clientDetails) {
        clientDAO.update(id, clientDetails);
        System.out.println("Updated client with id: " + id + ")");
    }

    public void deleteClient(long id) {
        clientDAO.delete(id);
        System.out.println("Deleted client with ID: " + id);
    }

    public void printAllClients() {
        List<Client> clients = getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            clients.forEach(client ->
                    System.out.println("ID: " + client.getId() + ", Name: " + client.getName() + ", Email: " + client.getEmail())
            );
        }
        System.out.println("\n");
    }
}
