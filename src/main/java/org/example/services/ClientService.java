package org.example.services;

import org.example.DAOs.ClientDAO;
import org.example.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientDAO clientDAO;

    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Transactional
    public void addClient(Client client) {
        clientDAO.save(client);
        logger.info("Added client: {} ({})", client.getName(), client.getEmail());
    }
@Transactional
    public List<Client> getAllClients() {
        List<Client> clients = clientDAO.getAll();
        logger.info("Fetched all clients: {} client(s) found.", clients.size());
        return clients;
    }

    @Transactional
    public Client getClientById(Long id) {
        Client client = clientDAO.findById(id);
        if (client != null) {
            logger.info("Found client by ID: {} - {}", id, client.getName());
        } else {
            logger.warn("No client found with ID: {}", id);
        }
        return client;
    }

    @Transactional
    public void updateClient(Client clientDetails) {
        clientDAO.update(clientDetails);
        logger.info("Updated client with id: {}", clientDetails.getId());
    }

    @Transactional
    public void deleteClient(Long id) {
        clientDAO.delete(id);
        logger.info("Deleted client with ID: {}", id);
    }

    @Transactional
    public void activateClient(Client client) {
        clientDAO.activateClient(client);
        logger.info("Activation of  client with ID: {}", client.getId());
    }
@Transactional
    public void printAllClients() {
        List<Client> clients = getAllClients();
        if (clients.isEmpty()) {
            logger.info("No clients found.");
        } else {
            clients.forEach(client ->
                    logger.info("Client added: Name: {}, Email: {}", client.getName(), client.getEmail())
            );
        }
        logger.info("\n");
    }
}
