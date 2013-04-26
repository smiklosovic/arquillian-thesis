package net.miklosovic.thesis.ejb.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import net.miklosovic.thesis.ejb.model.Customer;

/**
 * @author Stefan Miklosovic
 */
@Stateless
public class CustomerDAO implements CustomerDAOLocal {

    @PersistenceContext(unitName = "customers-test-unit")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(CustomerDAO.class.getName());

    @Override
    public void create(Customer user) {
        try {
            DAOUtils.notNull(user);
            em.persist(user);
        } catch (IllegalArgumentException ex) {
            logger.log(Level.INFO, "Unable to create a customer. Customer to persist is a null object.");
        } catch (EntityExistsException ex) {
            logger.log(Level.INFO, "Unable to create a customer. Customer to persist already exists.");
        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
    }

    @Override
    public void remove(Customer user) {
        try {
            DAOUtils.notNull(user);
            Customer toRemove = em.find(Customer.class, user.getId());
            em.remove(toRemove);
        } catch (IllegalArgumentException ex) {
            logger.log(Level.INFO, "Unable to create a customer. Customer to remove is a null object.");
        }
    }

    @Override
    public void update(Customer user) {
        try {
            DAOUtils.notNull(user);
            em.merge(user);
        } catch (IllegalArgumentException ex) {
            logger.log(Level.INFO, "Unable to remove the customer. It is not an entity or it is removed entity.");
        } catch (TransactionRequiredException ex) {
            logger.log(Level.INFO, "There is no transaction and and container-managed entity manager is of type "
                + "PersistenceContextType.TRANSACTION");
        }
    }

    @Override
    public Customer get(Long id) {
        try {
            DAOUtils.notNull(id);
            Customer user = em.find(Customer.class, id);
            return user;
        } catch (IllegalArgumentException ex) {
            logger.log(Level.INFO, "Id for getting the customer is null.");
            return null;
        }
    }

    @Override
    public Long getCount() {
        try {
            Query q = em.createQuery("select count(o) from Customer as o");
            return ((Long) q.getSingleResult());
        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getAll() {
        try {
            Query q = em.createQuery("select c from Customer c");
            return q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
            return null;
        }
    }

    @Override
    public Customer getByName(String name) {
        try {
            DAOUtils.notNull(name);
            Query q = em.createQuery("select o from Customer as o where o.name = :name");
            q.setParameter("name", name);
            return (Customer) q.getResultList().get(0);
        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
            return null;
        }
    }
}
