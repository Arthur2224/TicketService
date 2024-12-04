package org.example.DAOs;

import org.example.entities.Client;
import org.example.exceptions.DAOException;
import org.example.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO implements DAO<Client> {
    private final DataSource dataSource;
    private final TicketDAO ticketDAO;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientDAO(DataSource dataSource, TicketDAO ticketDAO, ClientMapper clientMapper) {
        this.dataSource = dataSource;
        this.ticketDAO = ticketDAO;
        this.clientMapper = clientMapper;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Client findById(Long id) {
        String sql = "SELECT id, name, email, created_at, updated_at FROM clients WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return clientMapper.mapToClient(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find client by ID: " + id, e);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        String sql = "SELECT id, name, email, created_at, updated_at FROM clients";
        List<Client> clients = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
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
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            clientMapper.mapToPreparedStatement(statement, client);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to save client: " + client, e);
        }
    }

    @Override
    public void update(Long id, Client updatedClient) {
        String sql = "UPDATE clients SET name = ?, email = ?, updated_at = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            clientMapper.mapToPreparedStatement(statement, updatedClient, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update client by ID: " + updatedClient.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            ticketDAO.deleteAllClientTickets(id);
            String sql = "DELETE FROM clients WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete client by ID: " + id, e);
        }
    }
}
