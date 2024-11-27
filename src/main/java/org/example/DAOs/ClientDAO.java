package org.example.DAOs;

import org.example.entities.Client;
import org.example.exceptions.DAOException;
import org.example.mappers.ClientMapper;
import org.example.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client> {
    private final Connection connection;
    private final ClientMapper clientMapper;
    private final TicketDAO ticketDAO;

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
        clientMapper = new ClientMapper();
        this.ticketDAO = new TicketDAO();
    }

    @Override
    public Client findById(Long id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return clientMapper.mapToClient(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find client by ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(clientMapper.mapToClient(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to retrieve all clients", e);
        }
        return clients;
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO clients (name, created_at, updated_at, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsAffected = clientMapper.mapToPreparedStatement(statement, client).executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Failed to save client: No rows affected");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to save client: " + client, e);
        }
    }

    @Override
    public void update(Long id, Client updatedClient) {
        String sql = "UPDATE clients SET name = ?, email = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsUpdated = clientMapper.mapToPreparedStatement(statement, updatedClient, id).executeUpdate();
            if (rowsUpdated == 0) {
                throw new DAOException("Failed to update client by ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to update client by ID: " + updatedClient.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        ticketDAO.deleteAllClientTickets(id);
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Failed to delete client by ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete client by ID: " + id, e);
        }
    }


}
