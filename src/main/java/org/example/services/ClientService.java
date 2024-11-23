package org.example.services;

import org.example.DAOs.ClientDAO;
import org.example.entities.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void addClient(Client client) {
        clientDAO.save(client);
    }

    public List<Client> getAllClients() {
        return clientDAO.getAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientDAO.findById(id);
    }

    public void updateClient(Client client, Client clientDetails) {
        clientDAO.update(client, clientDetails);
    }

    public void deleteClientById(Client client) {
        clientDAO.delete(client);
    }
}
