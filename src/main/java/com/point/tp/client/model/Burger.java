package com.point.tp.client.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Data
public class Burger {
    private Bacon bacon;
    private Bread bread;
    private Cheese cheese;
    private Salad salad;
    private Salsa salsa;
    private Steak steak;
    private Tomato tomato;

    private LocalDateTime release;

    public Burger() {
    }

    public Burger(Bacon bacon, Bread bread, Cheese cheese, Salad salad, Salsa salsa, Steak steak, Tomato tomato) {
        this.bacon = bacon;
        this.bread = bread;
        this.cheese = cheese;
        this.salad = salad;
        this.salsa = salsa;
        this.steak = steak;
        this.tomato = tomato;
    }

    public List<Ingredient> getIngredients() {
        return Arrays.asList(bacon, bread, cheese, salad, salsa, steak, tomato);
    }

    public boolean isHot(LocalDateTime now){
        return release.isAfter(now.minus(3, ChronoUnit.SECONDS));
    }

    /**
     * Add the ingredient to the burger.
     * All those intanceof are evil, but this is not the point :D
     * @param ingredient
     * @return
     */
    public Burger addIngredientToBurger(Ingredient ingredient) {
        if (ingredient instanceof Bacon) {
            setBacon((Bacon) ingredient);
        } else if (ingredient instanceof Bread) {
            setBread((Bread) ingredient);
        } else if (ingredient instanceof Steak) {
            setSteak((Steak) ingredient);
        } else if (ingredient instanceof Tomato) {
            setTomato((Tomato) ingredient);
        } else if (ingredient instanceof Cheese) {
            setCheese((Cheese) ingredient);
        } else if (ingredient instanceof Salad) {
            setSalad((Salad) ingredient);
        } else if (ingredient instanceof Salsa) {
            setSalsa((Salsa) ingredient);
        }
        return this;
    }
}

