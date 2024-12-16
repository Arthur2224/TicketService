package org.example.DAOs;

import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.utilities.SessionFactoryProvider;

import java.util.List;

public class TicketDAO implements DAO<Ticket> {

    @Override
    public Ticket findById(Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new DAOException("Failed to find ticket with ID: " + id, e);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Query<Ticket> query = session.createQuery("FROM Ticket", Ticket.class);
            return query.list();
        } catch (Exception e) {
            throw new DAOException("Failed to fetch all tickets", e);
        }
    }

    @Override
    public Ticket save(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Failed to save ticket", e);
        }
    }


    @Override
    public void update(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (ticket != null) {
                session.update(ticket);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Failed to update ticket ID: " + ticket.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                Client client = ticket.getClient();
                if (client != null) {
                    client.getTickets().remove(ticket);
                }
                session.delete(ticket);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Failed to delete ticket ID: " + id, e);
        }
    }

    public void deleteAllClientTickets(Long clientId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Ticket WHERE clientId = :clientId");
            query.setParameter("clientId", clientId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Failed to delete tickets for client ID: " + clientId, e);
        }
    }
}
