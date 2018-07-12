package pl.marcelbaumgardt.naukasqlonetomany.dao;

import org.springframework.stereotype.Repository;
import pl.marcelbaumgardt.naukasqlonetomany.model.Customer;
import pl.marcelbaumgardt.naukasqlonetomany.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerDao {


    @PersistenceContext
    EntityManager entityManager;


    public void save(Customer customer){
        List<Order> orders = customer.getOrders();
        if (!orders.isEmpty()) {
            for (Object o:orders) {
                entityManager.persist(o);
            }
        }
        entityManager.persist(customer);
    }

    public Customer get(Long id){
        Optional<Customer> optionalOfCustomer = Optional.ofNullable(entityManager.find(Customer.class, id));
        //Customer customer = entityManager.find(Customer.class, id);
        optionalOfCustomer.ifPresent(customer -> customer.getOrders().size());
        return optionalOfCustomer.orElse(null);
    }

    public void update(Customer customer){
        List<Order> orders = customer.getOrders();
        if (!orders.isEmpty()) {
            for (Order o:orders) {
                entityManager.merge(o);

            }
            customer.setOrders(orders);
        }
        entityManager.merge(customer);
    }
    
    public void remove(Customer customer){

        Customer objectToRemove = entityManager.merge(customer);
        List<Order> orders = objectToRemove.getOrders();
        for (Order o:orders) {
            entityManager.remove(o);
        }
        entityManager.remove(objectToRemove);
    }

}
