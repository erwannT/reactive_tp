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

        // TODO 4 Cook ingredients and then a burger using CookClient
        // 1
        Cheese cheese = null;
        // 2
        Bacon bacon = null;
        // 3
        Bacon cookBacon = null;
        // 4
        Bread bread = null;
        // 5
        Bread cutBread = null;
        // 6
        Salad salad = null;
        // 7
        Salsa salsa = null;
        // 8
        Steak steak = null;
        // 9
        Steak cookSteak = null;
        // 10
        Tomato tomato = null;

        // 11
        Burger burger = null;

        // 12
        return null;
    }
}
