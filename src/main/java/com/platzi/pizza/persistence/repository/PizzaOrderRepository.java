package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrderEntity, Integer> {
}
