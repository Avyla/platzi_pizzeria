package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.persistence.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaOrderService {

    private final PizzaOrderRepository pizzaOrderRepository;

    @Autowired
    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public List<PizzaOrderEntity> findAll(){
        return pizzaOrderRepository.findAll();
    }
}
