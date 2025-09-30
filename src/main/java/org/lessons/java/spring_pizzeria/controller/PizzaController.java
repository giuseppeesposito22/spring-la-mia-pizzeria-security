package org.lessons.java.spring_pizzeria.controller;

import java.util.List;

import org.lessons.java.spring_pizzeria.model.Deal;
import org.lessons.java.spring_pizzeria.model.Ingredient;
import org.lessons.java.spring_pizzeria.model.Pizza;
import org.lessons.java.spring_pizzeria.repository.IngredientRepository;
import org.lessons.java.spring_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repo;
    
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "name", required = false) String name){

        List<Pizza> pizzas;

        if (name != null && !name.isBlank()) {
            pizzas = repo.findByNameContainingIgnoreCase(name);
        }
        else{
             pizzas = repo.findAll();
        }

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id){

        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }


    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizzas/create";
    }
    

    @PostMapping("/create")
    public String store(Model model, @Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
        model.addAttribute("ingredients", ingredientRepository.findAll());

            return "/pizzas/create";
        }

        repo.save(pizza);

        return"redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){

        model.addAttribute("pizza", repo.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizzas/edit";
    }


    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {
        model.addAttribute("ingredients", ingredientRepository.findAll());

            return "/pizzas/edit";
        }

        repo.save(pizza);
        return"redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        repo.deleteById(id);
        return "redirect:/pizzas";
    }

    @GetMapping("/deal/{id}")
    public String deal(Model model, @PathVariable Integer id){
        
        Deal deal = new Deal();

        deal.setPizza(repo.findById(id).get());

        model.addAttribute("deal", deal);


        return"deals/create-or-edit";
        }

}
