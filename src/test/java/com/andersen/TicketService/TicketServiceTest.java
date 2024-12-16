package com.andersen.TicketService;

import org.example.DAOs.TicketDAO;
import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.example.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketDAO ticketDAO;
    private TicketService ticketService;

    // Setup for each test. Initializes mock objects and the service under test.
    @BeforeEach
    void setup() {
        ticketDAO = mock(TicketDAO.class);
        ticketService = new TicketService(ticketDAO);
    }

    // Test for adding a ticket. Includes positive, negative, and corner cases.
    @Test
    void addTicket_ValidTicket() {
        // Positive case: Valid ticket is added successfully
        Ticket ticket = new Ticket();
        ticket.setId(1L);

        // Assuming the method returns the saved ticket
        Ticket result = ticketService.addTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket.getId(), result.getId());
        verify(ticketDAO, times(1)).save(ticket);
    }


    @Test
    void addTicket_NullTicket() {
        // Negative case: Null ticket should throw an IllegalArgumentException
        Ticket invalidTicket = null;
        assertThrows(IllegalArgumentException.class, () -> ticketService.addTicket(invalidTicket));
    }

    @Test
    void addTicket_IncompleteTicket() {
        // Corner case: Ticket with missing required fields (ID) should throw an IllegalArgumentException
        Ticket incompleteTicket = new Ticket(); // No ID or other fields set
        assertThrows(IllegalArgumentException.class, () -> ticketService.addTicket(incompleteTicket));
    }


    // Test for updating an existing ticket. Validates the update process.
    @Test
    void updateTicket() {
        // Create an existing ticket to be updated
        Ticket existingTicket = new Ticket("Hall 1", 123, true, StadiumSectors.A, 10.0, new BigDecimal("50.00"), new Client());
        existingTicket.setId(1L);

        // Create an updated ticket with new details
        Ticket updatedTicket = new Ticket("Hall 1", 123, false, StadiumSectors.B, 15.0, new BigDecimal("55.00"), new Client());
        updatedTicket.setId(1L);

        // Mock the DAO to return the existing ticket for updating
        when(ticketDAO.findById(existingTicket.getId())).thenReturn(existingTicket);

        // Call the service method and verify that the ticket is updated
        ticketService.updateTicket(updatedTicket);
        verify(ticketDAO, times(1)).update(updatedTicket);
    }

    // Test for updating a ticket with a null ticket object. Should throw an exception.
    @Test
    void updateTicket_NullTicket() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.updateTicket(null);
        });

        assertEquals("Ticket cannot be null", exception.getMessage());
    }

    // Test for updating a ticket with a null ID. Should throw an exception.
    @Test
    void updateTicket_NullId() {
        Ticket ticketWithNullId = new Ticket("Hall 2", 456, true, StadiumSectors.C, 12.0, new BigDecimal("60.00"), new Client());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.updateTicket(ticketWithNullId);
        });

        assertEquals("ID cannot be null", exception.getMessage());
    }

    // Test for updating a ticket that does not exist in the database. Should throw an exception.
    @Test
    void updateTicket_TicketNotFound() {
        // Create a ticket with a non-existing ID
        Ticket nonExistingTicket = new Ticket("Hall 3", 789, false, StadiumSectors.A, 8.0, new BigDecimal("45.00"), new Client());
        nonExistingTicket.setId(2L);

        // Mock the behavior of the DAO to return null, indicating the ticket doesn't exist
        when(ticketDAO.findById(nonExistingTicket.getId())).thenReturn(null);

        // Attempt to update the ticket and expect an exception
        assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicket(nonExistingTicket),
                "Ticket with ID 2 not found");

        // Verify that update was never called for a non-existing ticket
        verify(ticketDAO, never()).update(nonExistingTicket);
    }

    // Test for retrieving all tickets. Validates various list scenarios.
    @Test
    void getAllTickets_ValidList() {
        // Positive case: Valid list of tickets is returned
        List<Ticket> tickets = Collections.singletonList(new Ticket());
        when(ticketDAO.getAll()).thenReturn(tickets);
        List<Ticket> result = ticketService.getAllTickets();
        assertEquals(1, result.size());
    }

    @Test
    void getAllTickets_EmptyList() {
        // Negative case: Empty list (no tickets)
        when(ticketDAO.getAll()).thenReturn(Collections.emptyList());
        List<Ticket> result = ticketService.getAllTickets();
        assertEquals(0, result.size());
    }

    @Test
    void getAllTickets_LargeList() {
        // Corner case: Large list of tickets (simulate high-volume scenario)
        List<Ticket> largeTicketList = Collections.nCopies(1000, new Ticket());
        when(ticketDAO.getAll()).thenReturn(largeTicketList);
        List<Ticket> result = ticketService.getAllTickets();
        assertEquals(1000, result.size());
    }


    // Test for retrieving a ticket by ID. Includes positive, negative, and corner cases.
    @Test
    void getTicketById_FoundTicket() {
        // Positive case: Found ticket
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(ticketDAO.findById(1L)).thenReturn(ticket);
        Ticket result = ticketService.getTicketById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getTicketById_NotFound() {
        // Negative case: Ticket not found
        when(ticketDAO.findById(1L)).thenReturn(null);
        Ticket result = ticketService.getTicketById(1L);
        assertNull(result);
    }

    @Test
    void getTicketById_EdgeCaseId() {
        // Corner case: Edge ID (maximum long value)
        Ticket edgeCaseTicket = new Ticket();
        edgeCaseTicket.setId(Long.MAX_VALUE);
        when(ticketDAO.findById(Long.MAX_VALUE)).thenReturn(edgeCaseTicket);
        Ticket result = ticketService.getTicketById(Long.MAX_VALUE);
        assertNotNull(result);
        assertEquals(Long.MAX_VALUE, result.getId());
    }


    // Test for deleting a ticket. Includes positive, negative, and corner cases.
    @Test
    void deleteTicket_SuccessfulDeletion() {
        // Positive case: Successfully delete ticket
        long ticketId = 1L;
        ticketService.delete(ticketId);
        verify(ticketDAO, times(1)).delete(ticketId);
    }

    @Test
    void deleteTicket_TicketNotFound() {
        // Negative case: Ticket ID does not exist, should throw exception
        long invalidTicketId = 999L;
        doThrow(new IllegalArgumentException("Ticket not found")).when(ticketDAO).delete(invalidTicketId);
        assertThrows(IllegalArgumentException.class, () -> ticketService.delete(invalidTicketId));
    }

    @Test
    void deleteTicket_EdgeCaseZeroId() {
        // Corner case: Delete ticket with ID 0 (edge case)
        long cornerTicketId = 0L;
        ticketService.delete(cornerTicketId);
        verify(ticketDAO, times(1)).delete(cornerTicketId);
    }


    // Test for printing all tickets. Verifies different scenarios of printing tickets.
    @Test
    void printAllTickets_WithMultipleTickets() {
        // Positive case: Print all tickets in the list
        Client client = new Client();
        client.setName("John Doe");

        Ticket ticket1 = new Ticket("Hall 3", 123, true, StadiumSectors.C, 10.0, new BigDecimal("50.00"), client);
        Ticket ticket2 = new Ticket("Hall 5", 124, false, StadiumSectors.A, 15.0, new BigDecimal("75.00"), client);

        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        // Mocking the DAO to return the list of tickets
        when(ticketDAO.getAll()).thenReturn(tickets);

        ticketService.printAllTickets();

        // Verifying that the getAll method was called once
        verify(ticketDAO, times(1)).getAll();
    }


    @Test
    void printAllTickets_WithNoTickets() {
        // Negative case: No tickets to print
        when(ticketDAO.getAll()).thenReturn(Collections.emptyList());

        ticketService.printAllTickets();

        verify(ticketDAO, times(1)).getAll();
    }

    @Test
    void printAllTickets_WithSingleTicket() {
        // Corner case: Print a single ticket
        Client client = new Client();
        client.setName("John Doe");

        Ticket singleTicket = new Ticket("Hall 1", 456, false, StadiumSectors.A, 5.0, new BigDecimal("25.00"), client);
        List<Ticket> singleTicketList = Collections.singletonList(singleTicket);
        when(ticketDAO.getAll()).thenReturn(singleTicketList);

        ticketService.printAllTickets();

        verify(ticketDAO, times(1)).getAll();
    }

}
