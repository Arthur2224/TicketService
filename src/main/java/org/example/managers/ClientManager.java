package org.example.managers;

import org.example.entities.Client;
import org.example.services.ClientService;

import java.util.List;
import java.util.Optional;

public class ClientManager {
    private final ClientService clientService;

    public ClientManager(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(Client client) {
        clientService.addClient(client);
        System.out.println("Added client: " + client.getName() + " (" + client.getEmail() + ")");
    }

    public void deleteClientByEmail(String email) {
        List<Client> clients = clientService.getAllClients();
        Client clientToDelete = findClientByEmail(clients, email);
        if (clientToDelete != null) {
            clientService.deleteClientById(clientToDelete);
            System.out.println("Deleted client with email: " + email);
        } else {
            System.out.println("No client found with email: " + email);
        }
    }

    public void deleteClientById(long id) {
        List<Client> clients = clientService.getAllClients();
        Client clientToDelete = findClientById(clients, id);
        if (clientToDelete != null) {
            clientService.deleteClientById(clientToDelete);
            System.out.println("Deleted client by ID: " + id);
        } else {
            System.out.println("No client found by ID: " + id);
        }
    }

    public void updateClientEmail(Long clientId, String newEmail) {
        Optional<Client> optionalClient = clientService.getClientById(clientId);
        optionalClient.ifPresentOrElse(
                client -> {
                    clientService.updateClient(client, new Client(client.getName(), newEmail));
                    System.out.println("Updated client email to: " + newEmail);
                },
                () -> System.out.println("No client found with ID: " + clientId)
        );
    }

    public void printAllClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            clients.forEach(client ->
                    System.out.println("ID: " + client.getId() + ", Name: " + client.getName() + ", Email: " + client.getEmail())
            );
        }
    }

    private Client findClientByEmail(List<Client> clients, String email) {
        return clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    private Client findClientById(List<Client> clients, long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
