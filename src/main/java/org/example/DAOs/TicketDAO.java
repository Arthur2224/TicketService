package org.example.DAOs;

import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.example.exceptions.DAOException;
import org.example.utilities.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAO implements DAO<Ticket> {

    private final Connection connection;

    public TicketDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Optional<Ticket> findById(long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapToTicket(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                tickets.add(mapToTicket(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        String sql = "INSERT INTO tickets (client_id, concert_hall, event_code, is_promo, stadium_sector, max_backpack_weight, price, creation_datetime,id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, ticket.getClient().getId());
            statement.setString(2, ticket.getConcertHall());
            statement.setInt(3, ticket.getEventCode());
            statement.setBoolean(4, ticket.isPromo());
            statement.setString(5, ticket.getStadiumSector().name());
            statement.setDouble(6, ticket.getMaxBackpackWeight());
            statement.setBigDecimal(7, ticket.getPrice());
            statement.setTimestamp(8,Timestamp.valueOf(ticket.getCreationDateTime() != null ? ticket.getCreationDateTime() : LocalDateTime.now()));
            statement.setLong(9,ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Ticket ticket, Ticket temp) {
        String sql = "UPDATE tickets SET client_id = ?, concert_hall = ?, event_code = ?, is_promo = ?, stadium_sector = ?, max_backpack_weight = ?, price = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, temp.getClient().getId());
            statement.setString(2, temp.getConcertHall());
            statement.setInt(3, temp.getEventCode());
            statement.setBoolean(4, temp.isPromo());
            statement.setString(5, temp.getStadiumSector().name());
            statement.setDouble(6, temp.getMaxBackpackWeight());
            statement.setBigDecimal(7, temp.getPrice());
            statement.setLong(8, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Ticket mapToTicket(ResultSet resultSet) throws SQLException {
        long clientId = resultSet.getLong("client_id");
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findById(clientId).orElseThrow(() ->
                new SQLException("Client not found with ID: " + clientId)
        );

        return Ticket.builder()
                .id(resultSet.getLong("id"))
                .client(client)
                .concertHall(resultSet.getString("concert_hall"))
                .eventCode(resultSet.getInt("event_code"))
                .isPromo(resultSet.getBoolean("is_promo"))
                .stadiumSector(StadiumSectors.valueOf(resultSet.getString("stadium_sector")))
                .maxBackpackWeight(resultSet.getDouble("max_backpack_weight"))
                .price(resultSet.getBigDecimal("price"))
                .build();
    }
    public void deleteTicketsByClientId(long clientId) {
        String sql = "DELETE FROM tickets WHERE client_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No tickets to delete for client ID: " + clientId);
                return;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to delete tickets for client ID: " + clientId, e);
        }
    }

}
