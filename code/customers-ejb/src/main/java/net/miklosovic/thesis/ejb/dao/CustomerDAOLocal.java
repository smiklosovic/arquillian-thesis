package net.miklosovic.thesis.ejb.dao;

import java.util.List;
import javax.ejb.Local;
import net.miklosovic.thesis.ejb.model.Customer;

/**
 * @author Stefan Miklosovic
 */
@Local
public interface CustomerDAOLocal {

    /**
     * This method creates a customer.
     * 
     * @param customer customer to create
     */
    void create(Customer customer);

    /**
     * This method removes a customer.
     * 
     * @param customer customer to remove
     */
    void remove(Customer customer);

    /**
     * This method updates already existing customer.
     * 
     * @param customer customer to update
     */
    void update(Customer customer);

    /**
     * This method gets a customer of some {@code id}.
     * 
     * @param id id of the customer we want to get
     * @return customer of specified {@code id}, null otherwise
     */
    Customer get(Long id);

    /**
     * This method returns count of customers in our simple world.
     * 
     * @return number of the all customers
     */
    Long getCount();

    /**
     * This method returns all customers in our simple world.
     * 
     * @return a list of all customers, if there are no customers, the returned list is an empty list, not null, null is
     *         returned when there was an error
     */
    List<Customer> getAll();

    /**
     * This method returns customer by its name
     * 
     * @param name name of the customer to get
     * @return customer of the specific name
     */
    public Customer getByName(String name);
}
