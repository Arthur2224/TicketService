package org.example.controllers;

import org.example.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling client-related views and navigation.
 * Provides the endpoint for displaying the dashboard.
 */
@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Displays the dashboard view.
     *
     * @return the name of the dashboard view
     */
    @GetMapping("/dashboard")
    public String home() {
        return "dashboard";
    }
}
