package org.example.services;

import org.example.entities.Client;
import org.example.repositories.ClientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of UserDetailsService that loads user information from the database.
 * This service is used by Spring Security for authenticating and authorizing users.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Loads a user by their email for authentication.
     *
     * @param email The email of the user to be loaded
     * @return UserDetails containing the user's email, password, and roles
     * @throws UsernameNotFoundException if the user cannot be found by email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Loading user by email: " + email);

        if (email == null || email.isEmpty()) {
            System.out.println("Email is null or empty!");
            throw new UsernameNotFoundException("Email cannot be null or empty");
        }

        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));

        System.out.println("Client found: " + client.getEmail());

        // Returning a UserDetails object with the client's details
        return User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .roles(client.isAdmin() ? "ADMIN" : "USER")  // Assigning role based on admin status
                .build();
    }
}
