package org.example.repositories;

import org.example.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByClientId(Long clientId);
}
