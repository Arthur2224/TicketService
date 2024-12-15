package org.example.mappers;

import org.example.DAOs.ClientDAO;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class TicketMapper {
    ClientDAO clientDAO;
    @Autowired
    @Lazy
    public TicketMapper(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public Ticket mapToTicket(ResultSet resultSet) throws SQLException {
        return Ticket.builder()
                .id(resultSet.getLong("id"))
                .client(clientDAO.findById(resultSet.getLong("client_id")))
                .concertHall(resultSet.getString("concert_hall"))
                .eventCode(resultSet.getInt("event_code"))
                .isPromo(resultSet.getBoolean("is_promo"))
                .stadiumSector(StadiumSectors.valueOf(resultSet.getString("stadium_sector")))
                .maxBackpackWeight(resultSet.getDouble("max_backpack_weight"))
                .price(resultSet.getBigDecimal("price"))
                .build();
    }
    public PreparedStatement mapToPreparedStatementSave(PreparedStatement statement, Ticket ticket) throws SQLException {
        statement.setLong(1, ticket.getClient().getId());
        statement.setString(2, ticket.getConcertHall());
        statement.setInt(3, ticket.getEventCode());
        statement.setBoolean(4, ticket.isPromo());
        statement.setString(5, ticket.getStadiumSector().name());
        statement.setDouble(6, ticket.getMaxBackpackWeight());
        statement.setBigDecimal(7, ticket.getPrice());
        statement.setTimestamp(8, Timestamp.valueOf(ticket.getCreationDateTime() != null ? ticket.getCreationDateTime() : LocalDateTime.now()));
        statement.setLong(9, ticket.getId());
        return statement;
    }
    public PreparedStatement mapToPreparedStatementUpdate(PreparedStatement statement, Ticket updatedTicket, Long ticketId) throws SQLException {
        statement.setLong(1, updatedTicket.getClient().getId());
        statement.setString(2, updatedTicket.getConcertHall());
        statement.setInt(3, updatedTicket.getEventCode());
        statement.setBoolean(4, updatedTicket.isPromo());
        statement.setString(5, updatedTicket.getStadiumSector().name());
        statement.setDouble(6, updatedTicket.getMaxBackpackWeight());
        statement.setBigDecimal(7, updatedTicket.getPrice());
        statement.setLong(8, ticketId);
        return statement;
    }
}
