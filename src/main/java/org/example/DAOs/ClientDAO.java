package org.example.DAOs;

import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDAO implements DAO<Client> {
    private final SessionFactory sessionFactory;

    public ClientDAO() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
    }

    @Override
    public Client findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT c FROM Client c LEFT JOIN FETCH c.tickets WHERE c.id = :id", Client.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Failed to find client by ID: " + id, e);
        }
    }


    @Override
    public List<Client> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client", Client.class).getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to retrieve all clients", e);
        }
    }

    @Override
    public Client save(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            return client;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DAOException("Failed to save client: " + client, e);
        }
    }

    @Override
    public void update(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            if (client.getTickets() != null) {
                for (Ticket ticket : client.getTickets()) {
                    session.update(ticket);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DAOException("Failed to update client and their tickets for client ID: " + client.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DAOException("Failed to delete client by ID: " + id, e);
        }
    }

}
