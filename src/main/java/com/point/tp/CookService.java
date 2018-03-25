package com.point.tp;

import com.point.tp.client.CookClient;
import com.point.tp.client.model.*;

/**
 * Cooks a {@link Burger}.
 * In order to cook a burger you need to :
 * <ul>
 *     <li>1 : Take a piece of cheese</li>
 *     <li>2 : Take a piece of bacon</li>
 *     <li>3 : Cook the bacon</li>
 *     <li>4 : Take a piece of bread</li>
 *     <li>5 : Cut the bread</li>
 *     <li>6 : Take some salad</li>
 *     <li>7 : Take some salsa</li>
 *     <li>8 : Take a steak</li>
 *     <li>9 : Cook the steak</li>
 *     <li>10 : Take a tomato</li>
 *     <li>11 : Make a new burger with all those ingredients</li>
 *     <li>12 : Cook the burger</li>
 * </ul>
 */
public class CookService {

    /**
     * Cooks a {@link Burger} !
     * @return
     */
    public Burger cookBurger(){

        // Initializes the CookClient
        CookClient cookClient = new CookClient();

        // 1
        Cheese cheese = cookClient.takeCheese();
        // 2
        Bacon bacon = cookClient.takeBacon();
        // 3
        Bacon cookBacon = cookClient.cookBacon(bacon);
        // 4
        Bread bread = cookClient.takeBread();
        // 5
        Bread cutBread = cookClient.cutBread(bread);
        // 6
        Salad salad = cookClient.takeSalad();
        // 7
        Salsa salsa = cookClient.takeSalsa();
        // 8
        Steak steak = cookClient.takeSteak();
        // 9
        Steak cookSteak = cookClient.cookSteak(steak);
        //10
        Tomato tomato = cookClient.takeTomato();

        // 11
        Burger burger = new Burger();
        burger.setBacon(cookBacon);
        burger.setBread(cutBread);
        burger.setCheese(cheese);
        burger.setSalad(salad);
        burger.setSalsa(salsa);
        burger.setSteak(cookSteak);
        burger.setTomato(tomato);

        // 12
        return cookClient.cook(burger);
    }

}
