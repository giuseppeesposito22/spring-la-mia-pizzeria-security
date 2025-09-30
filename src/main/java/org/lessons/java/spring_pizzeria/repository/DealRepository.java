package org.lessons.java.spring_pizzeria.repository;

import org.lessons.java.spring_pizzeria.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer>{

}
