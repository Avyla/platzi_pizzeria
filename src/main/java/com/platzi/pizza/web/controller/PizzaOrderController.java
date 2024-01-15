package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;

    @Autowired
    public PizzaOrderController(PizzaOrderService pizzaOrderService){
        this.pizzaOrderService = pizzaOrderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaOrderEntity>> findAll(){
        return ResponseEntity.ok(this.pizzaOrderService.findAll());
    }

}
