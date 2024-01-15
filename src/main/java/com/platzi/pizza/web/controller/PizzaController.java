package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /* Se podria decir que la diferencia entre @PathVarible y @RequestParam es que @PathVarible se utiliza para identificar
    uno o varios elementos mediante una busque y @RequestParam filtra los resultados de un busqueda a traves de informacion adicional*/
    @GetMapping("/all")
    public ResponseEntity<Page<PizzaEntity>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "8") Integer elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("pizza-available")
    public ResponseEntity<Page<PizzaEntity>> findByAvailable(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "8") Integer elements,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.findByAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable("price") Double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return pizzaService.findByName(name)
                .map(list -> new ResponseEntity<>(list,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/with/{ingrediente}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable("ingrediente") String ingrediente){
        return ResponseEntity.ok(this.pizzaService.findWith(ingrediente));
    }

    @GetMapping("/without/{ingrediente}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable("ingrediente") String ingrediente){
        return ResponseEntity.ok(this.pizzaService.getWithout(ingrediente));
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
