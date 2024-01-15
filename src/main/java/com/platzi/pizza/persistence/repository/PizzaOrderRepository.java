package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
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
}
