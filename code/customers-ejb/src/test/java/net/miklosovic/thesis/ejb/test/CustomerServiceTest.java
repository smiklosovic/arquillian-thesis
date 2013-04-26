package net.miklosovic.thesis.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import junit.framework.Assert;
import net.miklosovic.thesis.ejb.model.Customer;
import net.miklosovic.thesis.ejb.services.CustomerService;
import net.miklosovic.thesis.ejb.services.CustomerServiceLocal;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CustomerServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    @EJB
    private CustomerServiceLocal customerService;

    @Deployment
    public static Archive<?> createDeployment() {
        Archive<?> jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addPackage("net.miklosovic.thesis.ejb.dao")
            .addPackage("net.miklosovic.thesis.ejb.model")
            .addClass(CustomerService.class)
            .addClass(Customer.class)
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Deleting old records...");
        em.createQuery("delete from Customer").executeUpdate();
        utx.commit();
    }

    @Test
    public void testCustomer() {
        assertTrue(true);
    }

    @Test
    public void testCreateCustomer() {
        Customer Customer = new Customer();
        Customer.setName("Stefan");
        customerService.create(Customer);
        Customer Customer2 = new Customer();
        Customer2 = customerService.get(Customer.getId());
        Assert.assertEquals(Customer, Customer2);
    }

    @Test
    public void testUpdateCustomer() {
        Customer Customer = new Customer();
        Customer.setName("Stefan Miklosovic");
        customerService.create(Customer);
        Customer CustomerToUpdate = customerService.get(Customer.getId());
        CustomerToUpdate.setName("Stefan M");
        customerService.update(CustomerToUpdate);
        assertEquals("Stefan M", customerService.get(Customer.getId()).getName());
    }

    @Test
    public void testRemoveCustomer() {
        Customer Customer = new Customer("Jan");
        customerService.create(Customer);
        Customer Customer2 = customerService.get(Customer.getId());
        assertEquals(Customer, Customer2);
        customerService.remove(Customer);
        assertNull(customerService.get(Customer.getId()));
    }

    @Test
    public void testGetCountOfCustomers() {
        Customer Customer = new Customer("Pavel");
        Customer Customer2 = new Customer("Jiri");
        Customer Customer3 = new Customer("Zdenek");

        customerService.create(Customer);
        customerService.create(Customer2);
        customerService.create(Customer3);

        assertEquals(customerService.getCount(), new Long(3));

        customerService.remove(Customer2);

        assertEquals(customerService.getCount(), new Long(2));

        customerService.remove(Customer);
        customerService.remove(Customer3);

        assertEquals(customerService.getCount(), new Long(0));
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer = new Customer("Pavel");
        Customer customer2 = new Customer("Karel");
        Customer customer3 = new Customer("Marta");

        customerService.create(customer);
        customerService.create(customer2);
        customerService.create(customer3);

        List<Customer> saved = new ArrayList<Customer>();
        saved.add(customer);
        saved.add(customer2);
        saved.add(customer3);

        List<Customer> returned = customerService.getAll();
        assertTrue(compareCustomerLists(saved, returned));
    }

    private boolean compareCustomerLists(List<Customer> list1, List<Customer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
