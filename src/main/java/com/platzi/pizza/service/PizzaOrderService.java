package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PizzaOrderService {

    private final PizzaOrderRepository pizzaOrderRepository;
    private final String DELIVERY = "D";
    private final String CARRYOUT = "C";
    private final String ON_SITE = "S";

    @Autowired
    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public List<PizzaOrderEntity> getAll() {
        return pizzaOrderRepository.findAll();
    }

    public List<PizzaOrderEntity> getTodayOrders() {
        LocalDateTime date = LocalDate.now().atTime(0, 0);
        return this.pizzaOrderRepository.findAllByDateAfter(date);
    }

    public List<PizzaOrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.pizzaOrderRepository.findAllByMethodIn(methods);
    }

//    public List<PizzaOrderEntity> getCustomerOrders(String idCustomer){
//        return this.pizzaOrderRepository.findCustomerOrders(idCustomer);
//    }

    public List<PizzaOrderEntity> getCustomerOrders(String idCustomer) {
        return this.pizzaOrderRepository.findByIdCustomer(idCustomer);
    }

    public OrderSummary getSummary(Integer idOrder) {
        return this.pizzaOrderRepository.findSummary(idOrder);
    }
}
