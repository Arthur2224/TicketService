package org.example.DAOs;

import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.enums.StadiumSectors;
import org.example.enums.Status;
import org.example.exceptions.DAOException;
import org.example.services.TicketService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@PropertySource("classpath:app.properties")
@Repository
public class ClientDAO implements DAO<Client> {
    private final SessionFactory sessionFactory;
    private final TicketService ticketService;
    @Value("${app.update.enabled}")
    private String isUpdateEnabled;

    @Autowired
    public ClientDAO(SessionFactory sessionFactory, TicketService ticketService) {
        this.sessionFactory = sessionFactory;
        this.ticketService = ticketService;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Client findById(Long id) {
        try {
            return getSession().createQuery(
                            "FROM Client c LEFT JOIN FETCH c.tickets WHERE c.id = :id", Client.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Failed to find client by ID: " + id, e);
        }
    }

    @Override
    public List<Client> getAll() {
        try {
            return getSession().createQuery("FROM Client", Client.class).getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to retrieve all clients", e);
        }
    }

    @Override
    public void save(Client client) {
        try {
            getSession().save(client);
        } catch (Exception e) {
            throw new DAOException("Failed to save client: " + client, e);
        }
    }

    @Override
    public void update(Client updatedClient) {
        try {
            if (updatedClient.getStatus() != Status.ACTIVE || !Boolean.valueOf(isUpdateEnabled)) {
                throw new RuntimeException("Ability to update client is disabled");
            }
            getSession().merge(updatedClient);
        } catch (Exception e) {
            throw new DAOException("Failed to update client: " + updatedClient.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Client client = getSession().get(Client.class, id);
            if (client != null) {
                String hql = "DELETE FROM Ticket t WHERE t.client.id = :clientId";
                getSession().createQuery(hql)
                        .setParameter("clientId", id)
                        .executeUpdate();
                getSession().delete(client);
            } else {
                throw new DAOException("Failed to find client with ID: " + id);
            }
        } catch (Exception e) {
            throw new DAOException("Failed to delete client by ID: " + id, e);
        }
    }

    public void activateClient(Client client) {
        try {
            client.setStatus(Status.ACTIVE);
            getSession().saveOrUpdate(client);
            Ticket ticket = new Ticket("Match A", 101, true, StadiumSectors.C, 22.5, BigDecimal.valueOf(50.0), client);
            ticketService.addTicket(ticket);
        } catch (Exception e) {
            throw new DAOException("Failed to activate client with ID: " + client.getId(), e);
        }
    }
}
