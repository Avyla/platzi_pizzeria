package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrderEntity, Integer> {
    //Este metodo pretende traer todas las ordenes realizadas despues de las 00:00 horas del dia en el que se encuentre
    List<PizzaOrderEntity> findAllByDateAfter(LocalDateTime date);

    List<PizzaOrderEntity> findAllByMethodIn(List<String> methods);

    //    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :idCustomer", nativeQuery = true)
//    List<PizzaOrderEntity> findCustomerOrders(@Param("idCustomer") String idCustomer);
    List<PizzaOrderEntity> findByIdCustomer(String idCustomer);

    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, " +
                    "  po.date AS orderDate, po.total AS orderTotal, GROUP_CONCAT(pi.name) as pizzaName " +
                    "FROM pizza_order po " +
                    "    INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
                    "    INNER JOIN order_item oi ON po.id_order = oi.id_order " +
                    "    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
                    "WHERE po.id_order = :idOrder " +
                    "GROUP BY po.id_order, cu.name, po.date, po.total;", nativeQuery = true)
    OrderSummary findSummary(@Param("idOrder") Integer idOrder);
}
