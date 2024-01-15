package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        return pizzaRepository.findAll();
    }

    public PizzaEntity findByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity getById(Integer idPizza){
        return this.pizzaRepository.findById(idPizza)
                .orElse(null);
    }
    public List<PizzaEntity> findByAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    

    public List<PizzaEntity> findWith(String ingrediente){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingrediente);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public Boolean exists(Integer idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
