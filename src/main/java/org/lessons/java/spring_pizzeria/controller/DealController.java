package org.lessons.java.spring_pizzeria.controller;

import org.lessons.java.spring_pizzeria.model.Deal;
import org.lessons.java.spring_pizzeria.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("deal") Deal deal, BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {
            return "deals/create-or-edit";
        }

        dealRepository.save(deal);

        return "redirect:/pizzas/" + deal.getPizza().getId();
    }    

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        model.addAttribute("deal", dealRepository.findById(id).get());
        model.addAttribute("edit", true);

        return"deals/create-or-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("deal") Deal deal, BindingResult bindingResult, Model model){
        
        if (bindingResult.hasErrors()) {
            return"deals/create-or-edit";
        }

        dealRepository.save(deal);
        return"redirect:/pizzas/" + deal.getPizza().getId();
    }

}
