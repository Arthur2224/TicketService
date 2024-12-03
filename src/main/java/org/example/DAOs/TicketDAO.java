package org.example.DAOs;

import org.example.entities.Ticket;
import org.example.exceptions.DAOException;
import org.example.mappers.TicketMapper;
import org.example.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAO<Ticket> {

    private final Connection connection;
    private final TicketMapper ticketMapper;

    public TicketDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.ticketMapper = new TicketMapper();
    }

    @Override
    public Ticket findById(Long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return ticketMapper.mapToTicket(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find ticket with ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                tickets.add(ticketMapper.mapToTicket(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to fetch all tickets", e);
        }
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        String sql = "INSERT INTO tickets (client_id, concert_hall, event_code, is_promo, stadium_sector, max_backpack_weight, price, creation_datetime, id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsUpdated = ticketMapper.mapToPreparedStatementSave(statement, ticket).executeUpdate();
            if (rowsUpdated == 0) {
                throw new DAOException("Failed to save ticket by ID: " + ticket.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to save ticket ID: " + ticket.getId(), e);
        }
    }

    @Override
    public void update(Long id, Ticket updatedTicket) {
        String sql = "UPDATE tickets SET client_id = ?, concert_hall = ?, event_code = ?, is_promo = ?, stadium_sector = ?, max_backpack_weight = ?, price = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsUpdated =ticketMapper.mapToPreparedStatementUpdate(statement, updatedTicket, id).executeUpdate();
            if (rowsUpdated == 0) {
                throw new DAOException("Failed to update ticket by ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to update ticket ID: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No tickets to delete for client ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete ticket ID: " + id, e);
        }
    }

    public void deleteAllClientTickets(Long id) {
        String sql = "DELETE FROM tickets WHERE client_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No tickets to delete for client ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete ticket for client ID: " + id, e);
        }
    }


}
