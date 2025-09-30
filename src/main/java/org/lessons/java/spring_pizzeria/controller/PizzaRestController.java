package org.lessons.java.spring_pizzeria.controller;

import java.util.List;

import org.lessons.java.spring_pizzeria.model.Pizza;
import org.lessons.java.spring_pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> idex(@RequestParam(required = false) String name){

        if(name != null && !name.isEmpty()){
            return pizzaService.findPizzaByName(name);

        }

        return pizzaService.getAllPizzas();

   
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id){

        if(pizzaService.existById(id)){
           return new ResponseEntity<Pizza>(pizzaService.getPizzaById(id), HttpStatus.OK);
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza){
        return new ResponseEntity<Pizza>(pizzaService.createPizza(pizza), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id){

        if(pizzaService.existById(id)){
            pizza.setId(id);
            return new ResponseEntity<Pizza>(pizzaService.updatePizza(pizza), HttpStatus.OK);
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id){
        
        if(pizzaService.existById(id)){
            pizzaService.deletePizza(id);
            return new ResponseEntity<Pizza>(HttpStatus.OK);

        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }
}
