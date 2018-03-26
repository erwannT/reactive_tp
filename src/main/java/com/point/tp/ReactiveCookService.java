package com.point.tp;

import com.point.tp.client.ReactiveCookClient;
import com.point.tp.client.model.Bacon;
import com.point.tp.client.model.Bread;
import com.point.tp.client.model.Burger;
import com.point.tp.client.model.Steak;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Cooks a reactive {@link Burger} !
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
public class ReactiveCookService {


    /**
     * Cooks a {@link Burger}
     * @return a {@link Mono} having the burger
     */
    // TODO 5 Using reactiveCookClient & reactive operators, implement the cookBurger method
    public Mono<Burger> cookBurger() {

        // Initializes the ReactiveCookClient
        ReactiveCookClient reactiveCookClient = new ReactiveCookClient();

        // 2, 3
        Mono<Bacon> baconMono = null;

        // 8, 9
        Mono<Steak> steakMono = null;
        // 4, 5
        Mono<Bread> breadMono = null;

        // 1, 6, 7, 10, 11, 12
        // You might use Burger.addIngredientToBurger & reactiveCookClient.cook methods
        return null;
    }

    /**
     * Cooks a lot of burgers
     * @param howMany
     * @return a {@link Flux} having the burgers
     */
    // TODO 8 Implements a method to launch several cooking at the same time
    public Flux<Burger> cookBurger(int howMany) {
        return null;
    }

}
