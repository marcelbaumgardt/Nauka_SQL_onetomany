package pl.marcelbaumgardt.naukasqlonetomany.dao;

import org.springframework.stereotype.Repository;
import pl.marcelbaumgardt.naukasqlonetomany.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Repository
@Transactional
public class OrderDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }




}
