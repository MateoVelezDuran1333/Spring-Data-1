package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8" ) int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8" ) int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> with(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> without(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }



    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> get(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/cheapest/{precio}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double precio){
        return ResponseEntity.ok(this.pizzaService.getCheapest(precio));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() == null || this.pizzaService.exists(pizzaEntity.getIdPizza())){
            return  ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() != null && this.pizzaService.exists(pizzaEntity.getIdPizza())){
            return  ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if (this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
