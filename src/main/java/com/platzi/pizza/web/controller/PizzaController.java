package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaEntity>> findAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("pizza-available")
    public ResponseEntity<List<PizzaEntity>> findByAvailable(){
        return ResponseEntity.ok(this.pizzaService.findByAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.findByName(name));
    }

    @GetMapping("/with/{ingrediente}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable("ingrediente") String ingrediente){
        return ResponseEntity.ok(this.pizzaService.findWith(ingrediente));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> findById(@PathVariable("idPizza") Integer idPizza){
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity) {
        if(pizzaEntity.getIdPizza() == null || !pizzaService.exists(pizzaEntity.getIdPizza())){
            pizzaService.save(pizzaEntity);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        if(pizzaEntity.getIdPizza() != null && pizzaService.exists(pizzaEntity.getIdPizza())){
            pizzaService.save(pizzaEntity);
        }
        return ResponseEntity.badRequest().build();
    }
}
