package org.lessons.java.spring_pizzeria.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizzas")
public class Pizza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String name;


    private String description;
    private String img;

    @NotNull(message = "Il campo prezzo non può essere vuoto")
    @Min( value = 0, message = "Il prezzo non può essere negativo")
    private Double price;

    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<Deal> deals;

    @ManyToMany()
    @JoinTable(name = "ingredient_pizza", 
    joinColumns = @JoinColumn(name = "pizza_id"), 
    inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;


    public Pizza() {
    }

    public Pizza(Integer id, @NotBlank(message = "The name must not be null") String name, String description,
            String img, @NotNull @Min(0) Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
    public List<Deal> getDeals() {
        return deals;
    }
    
    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
    
    
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    
    @Override
    public String toString() {
        return "Pizza [id=" + id + ", name=" + name + ", description=" + description + ", img=" + img + ", price="
                + price + "]";
    }
}
