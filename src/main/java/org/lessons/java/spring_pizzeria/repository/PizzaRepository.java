package org.lessons.java.spring_pizzeria.repository;

import java.util.List;

import org.lessons.java.spring_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository  extends JpaRepository<Pizza, Integer>{

    public List<Pizza> findByNameContainingIgnoreCase(String name);

}
