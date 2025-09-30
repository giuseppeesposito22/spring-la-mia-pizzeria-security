package org.lessons.java.spring_pizzeria.service;

import java.util.List;


import org.lessons.java.spring_pizzeria.model.Pizza;
import org.lessons.java.spring_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    public List<Pizza> findPizzaByName(String name){
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza getPizzaById(Integer id){
        return pizzaRepository.findById(id).get();
    }

    public Pizza createPizza( Pizza pizza){
       return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Integer id){
        pizzaRepository.deleteById(id);
    }
    
    public boolean existById(Integer id){
        return pizzaRepository.existsById(id);
    }

    public boolean exixst(Pizza pizza){
        return pizzaRepository.existsById(pizza.getId());
    }

}
