package org.example.controllers;

import org.example.entities.Client;
import org.example.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing clients.
 * Provides endpoints for retrieving, creating, and deleting clients.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return a list of all clients
     */
    @GetMapping("/getAll")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Retrieves a client by its ID.
     * Returns null if the client is not found.
     *
     * @param id the ID of the client
     * @return the client if found, otherwise null
     */
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id).orElse(null);
    }

    /**
     * Saves a new client or updates an existing one.
     *
     * @param client the client to be saved
     * @return the saved client
     */
    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    /**
     * Deletes a client by its ID.
     *
     * @param id the ID of the client to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    /**
     * Retrieves a client by email.
     * Returns null if the client is not found.
     *
     * @param email the email of the client
     * @return the client if found, otherwise null
     */
    @GetMapping("/getByEmail/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email).orElse(null);
    }
}
