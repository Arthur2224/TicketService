package org.example.DAOs;

import org.example.entities.Ticket;
import org.example.exceptions.DAOException;
import org.example.mappers.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDAO implements DAO<Ticket> {

    private final DataSource dataSource;
    private final TicketMapper ticketMapper;

    @Autowired
    @Lazy
    public TicketDAO(DataSource dataSource, TicketMapper ticketMapper) {
        this.dataSource = dataSource;
        this.ticketMapper = ticketMapper;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Ticket findById(Long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
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
        String sql = "INSERT INTO tickets (client_id, concert_hall, event_code, is_promo, stadium_sector, max_backpack_weight, price, creation_datetime, id ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ticketMapper.mapToPreparedStatementSave(statement, ticket);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to save ticket with ID: " + ticket.getId(), e);
        }
    }

    @Override
    public void update(Long id, Ticket updatedTicket) {
        String sql = "UPDATE tickets SET client_id = ?, concert_hall = ?, event_code = ?, is_promo = ?, stadium_sector = ?, max_backpack_weight = ?, price = ? " +
                "WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ticketMapper.mapToPreparedStatementUpdate(statement, updatedTicket, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update ticket with ID: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Failed to delete ticket with ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete ticket with ID: " + id, e);
        }
    }
    @Transactional
    public void deleteAllClientTickets(Long clientId) {
        String sql = "DELETE FROM tickets WHERE client_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No tickets to delete for client ID: " + clientId);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete tickets for client ID: " + clientId, e);
        }
    }
}
