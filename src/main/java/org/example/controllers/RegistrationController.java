package org.example.controllers;

import org.example.entities.Client;
import org.example.enums.Status;
import org.example.repositories.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling client registration.
 * Provides endpoints for displaying registration form and processing registration.
 */
@Controller
public class RegistrationController {

    private final ClientRepository clientRepository;

    public RegistrationController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Endpoint to display the registration form.
     *
     * @return the name of the registration view
     */
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    /**
     * Endpoint to process the registration form.
     * Validates the input and creates a new client if the data is valid.
     *
     * @param name            the client's name
     * @param email           the client's email
     * @param password        the client's password
     * @param confirmPassword confirmation of the client's password
     * @return a redirect URL depending on the registration outcome:
     *         - Redirects to registration page with an error if passwords do not match
     *         - Redirects to error page if email is already taken
     *         - Redirects to login page after successful registration
     */
    @PostMapping("/register")
    public String registerClient(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String confirmPassword) {
        // If passwords do not match, redirect to registration page with an error
        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error";
        }

        if (clientRepository.existsByEmail(email)) {
            return "redirect:/error";
        }

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(new BCryptPasswordEncoder().encode(password));
        client.setStatus(Status.ACTIVE);
        client.setAdmin(false); // Default to non-admin user
        clientRepository.save(client);

        return "redirect:/login";
    }
}
