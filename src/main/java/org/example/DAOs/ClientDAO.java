package org.example.DAOs;

import org.example.entities.Client;
import org.example.exceptions.DAOException;
import org.example.utilities.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements DAO<Client> {
    private final Connection connection;

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Optional<Client> findById(long id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = Client.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                        .build();
                return Optional.of(client);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find client by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = Client.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                        .build();
                clients.add(client);
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
            statement.setString(1, client.getName());
            statement.setTimestamp(2, Timestamp.valueOf(client.getCreatedAt() != null ? client.getCreatedAt() : LocalDateTime.now()));
            statement.setTimestamp(3, Timestamp.valueOf(client.getUpdatedAt() != null ? client.getUpdatedAt() : LocalDateTime.now()));
            statement.setString(4, client.getEmail());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Failed to save client: No rows affected");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to save client: " + client, e);
        }
    }

    public void update(Client client, Client updatedClient) {
        String sql = "UPDATE clients SET name = ?, email = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedClient.getName());
            statement.setString(2, updatedClient.getEmail());
            updatedClient.onUpdate();
            statement.setTimestamp(3, Timestamp.valueOf(updatedClient.getUpdatedAt()));
            statement.setLong(4, client.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DAOException("Failed to update client by ID: " + client.getId());
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to update client by ID: " + client.getId(), e);
        }
    }

    public void delete(Client client) {
        String sql = "DELETE FROM clients WHERE id = ?";
        long id = client.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Failed to delete client by ID: " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete client by ID: " + id, e);
        }
    }
}
