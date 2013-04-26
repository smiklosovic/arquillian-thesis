package net.miklosovic.thesis.ejb.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.miklosovic.thesis.ejb.dao.CustomerDAOLocal;
import net.miklosovic.thesis.ejb.model.Customer;


/**
 * @author Stefan Miklosovic
 */
@Stateless
public class CustomerService implements CustomerServiceLocal {

    @EJB
    private CustomerDAOLocal customerDao;

    @Override
    public void create(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerDao.remove(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public Customer get(Long id) {
        return customerDao.get(id);
    }

    @Override
    public Long getCount() {
        return customerDao.getCount();
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer getByName(String name) {
        return customerDao.getByName(name);
    }
}
