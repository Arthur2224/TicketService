package org.example.controllers;

import org.example.exceptions.CustomLoginErrorHandler;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Controller for handling client login actions.
 * Provides functionality for displaying the login page and processing login attempts.
 */
@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;
    private final CustomLoginErrorHandler customErrorHandler;

    /**
     * Constructor for LoginController to inject dependencies.
     *
     * @param authenticationManager The authentication manager for handling login attempts
     * @param passwordEncoder       The password encoder for password verification
     * @param clientService         The service that provides client data operations
     * @param customErrorHandler    The custom error handler to manage login block states
     */
    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder,
                           ClientService clientService,
                           CustomLoginErrorHandler customErrorHandler) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.customErrorHandler = customErrorHandler;
    }

    /**
     * Displays the login page.
     * If login is blocked, redirects to the error page with a message.
     *
     * @param model the model to pass data to the view
     * @return the login view or error page if login is blocked
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        if (customErrorHandler.isBlockLogin()) {
            model.addAttribute("errorMessage", "Access blocked! The system is currently unavailable.");
            return "error"; // Redirect to the error page if login is blocked
        }
        return "login"; // Otherwise, display the login page
    }

    /**
     * Processes the login request.
     * Verifies the user's credentials and redirects to the dashboard on success.
     * Displays an error page if the credentials are invalid or the login is blocked.
     *
     * @param email    the user's email
     * @param password the user's password
     * @param model    the model to pass data to the view
     * @return a redirect to the dashboard on successful login or error page on failure
     */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            if (customErrorHandler.isBlockLogin()) {
                model.addAttribute("errorMessage", "Access blocked! The system is currently unavailable.");
                return "error";
            }

            // Retrieve the stored password hash for the client
            Optional<String> storedPasswordHash = clientService.getPasswordByEmail(email);

            if (storedPasswordHash.isPresent()) {
                String passwordHash = storedPasswordHash.get();

                // Check if the provided password matches the stored hash
                boolean matches = passwordEncoder.matches(password, passwordHash);

                if (matches) {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                    return "redirect:/dashboard"; // Redirect to the dashboard after successful login
                } else {
                    // Invalid password
                    model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
                    return "redirect:/error";
                }
            } else {
                // User not found
                model.addAttribute("errorMessage", "User not found. Please check your email.");
                return "redirect:/error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred during login. Please try again later.");
            return "redirect:/error";
        }
    }
}
