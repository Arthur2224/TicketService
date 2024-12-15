package org.example.DAOs;

import org.example.entities.Ticket;
import org.example.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAO implements DAO<Ticket> {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Ticket findById(Long id) {
        try {
            return getSession().get(Ticket.class, id);
        } catch (Exception e) {
            throw new DAOException("Failed to find ticket with ID: " + id, e);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try {
            return getSession().createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            throw new DAOException("Failed to fetch all tickets", e);
        }
    }

    @Override
    public void save(Ticket ticket) {
        try {
            getSession().save(ticket);
        } catch (Exception e) {
            throw new DAOException("Failed to save ticket with ID: " + ticket.getId(), e);
        }
    }

    @Override
    public void update(Ticket updatedTicket) {
        try {
            if (getSession().get(Ticket.class, updatedTicket.getId()) != null) {
                getSession().merge(updatedTicket);
            }
        } catch (Exception e) {
            throw new DAOException("Failed to update ticket with ID: " + updatedTicket.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Ticket ticket = getSession().get(Ticket.class, id);
            if (ticket != null) {
                getSession().delete(ticket);
            } else {
                throw new DAOException("Failed to delete ticket with ID: " + id);
            }
        } catch (Exception e) {
            throw new DAOException("Failed to delete ticket with ID: " + id, e);
        }
    }
}
