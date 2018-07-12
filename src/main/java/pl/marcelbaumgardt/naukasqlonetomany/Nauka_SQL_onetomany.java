package pl.marcelbaumgardt.naukasqlonetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.marcelbaumgardt.naukasqlonetomany.dao.CustomerDao;
import pl.marcelbaumgardt.naukasqlonetomany.dao.OrderDao;
import pl.marcelbaumgardt.naukasqlonetomany.model.Customer;
import pl.marcelbaumgardt.naukasqlonetomany.model.Order;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Nauka_SQL_onetomany {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Nauka_SQL_onetomany.class, args);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        Customer customer = new Customer("marcel", "baumgardt");
        List<Order> orders=new ArrayList<>();
        orders.add(new Order("zamowienie",45.65,"srata pierdadta"));
        orders.add(new Order("zamowienie2",54.65,"srata pierdadta albo nie"));
        orders.add(new Order("zamowienie3",64.65,"srata pierdadta albo nie  aa"));
        orders.add(new Order("zamowienie4",74.65,"srata pierdadta albo nie bb"));
        orders.add(new Order("zamowienie5",84.65,"srata pierdadta albo nie  cc"));

        for (Order o:orders) {
            o.setCustomer(customer);
        }

        customer.setOrders(orders);
        customerDao.save(customer);
        customerDao.get(1L);
        customer.setFirstName("Pawel");
        customerDao.update(customer);
        customerDao.get(1L);
        customerDao.remove(customer);
        customerDao.get(1L);



        /*
        customerDao.save(customer);

        for (Order o:orders) {
            o.setCustomer(customer);
            orderDao.save(o);
        }*/

        //customer.setOrders(orders);

        Customer customer1 = customerDao.get(1L);

        System.out.println(customer1);

    }
}
