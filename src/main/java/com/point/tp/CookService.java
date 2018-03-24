package com.point.tp;

import com.point.tp.client.CookClient;
import com.point.tp.client.model.*;

public class CookService {
    public Burger cookBurger(){
        CookClient cookClient = new CookClient();

        Cheese cheese = cookClient.takeCheese();

        Bacon bacon = cookClient.takeBacon();
        Bacon cookBacon = cookClient.cookBacon(bacon);

        Bread bread = cookClient.takeBread();
        Bread cutBread = cookClient.cutBread(bread);

        Salad salad = cookClient.takeSalad();

        Salsa salsa = cookClient.takeSalsa();

        Steak steack = cookClient.takeSteack();
        Steak cookSteak = cookClient.cookSteak(steack);

        Tomato tomato = cookClient.takeTomato();

        Burger burger = new Burger();
        burger.setBacon(cookBacon);
        burger.setBread(cutBread);
        burger.setCheese(cheese);
        burger.setSalad(salad);
        burger.setSalsa(salsa);
        burger.setSteak(cookSteak);
        burger.setTomato(tomato);

        return cookClient.cook(burger);
    }

}
