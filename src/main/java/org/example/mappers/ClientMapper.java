package org.example.mappers;

import org.example.entities.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ClientMapper {
    public Client mapToClient(ResultSet resultSet) throws SQLException {
        return Client.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                .build();

    }

    public PreparedStatement mapToPreparedStatement(PreparedStatement statement, Client client) throws SQLException {
        statement.setString(1, client.getName());
        statement.setTimestamp(2, Timestamp.valueOf(client.getCreatedAt() != null ? client.getCreatedAt() : LocalDateTime.now()));
        statement.setTimestamp(3, Timestamp.valueOf(client.getUpdatedAt() != null ? client.getUpdatedAt() : LocalDateTime.now()));
        statement.setString(4, client.getEmail());
        return statement;
    }

    public PreparedStatement mapToPreparedStatement(PreparedStatement statement, Client updatedClient, Long clientId) throws SQLException {
        statement.setString(1, updatedClient.getName());
        updatedClient.onUpdate();
        statement.setTimestamp(3, Timestamp.valueOf(updatedClient.getUpdatedAt() != null ? updatedClient.getUpdatedAt() : LocalDateTime.now()));
        statement.setString(2, updatedClient.getEmail());
        statement.setLong(4, clientId);
        return statement;
    }
}

