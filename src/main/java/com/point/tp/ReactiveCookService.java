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
    public Mono<Burger> cookBurger() {

        // Initializes the ReactiveCookClient
        ReactiveCookClient reactiveCookClient = new ReactiveCookClient();

        // 2, 3
        Mono<Bacon> baconMono = reactiveCookClient
            .takeBacon()
            .flatMap(reactiveCookClient::cookBacon);

        // 8, 9
        Mono<Steak> steakMono = reactiveCookClient
            .takeSteak()
            .flatMap(reactiveCookClient::cookSteak);
        // 4, 5
        Mono<Bread> breadMono = reactiveCookClient
            .takeBread()
            .flatMap(reactiveCookClient::cutBread);


        return Flux.merge(
            baconMono,
            steakMono,
            breadMono,
            // 1
            reactiveCookClient.takeCheese(),
            // 6
            reactiveCookClient.takeSalad(),
            // 7
            reactiveCookClient.takeSalsa(),
            // 10
            reactiveCookClient.takeTomato()
        ).
            // 11
            reduce(new Burger(), Burger::addIngredientToBurger)
            // 12
            .flatMap(reactiveCookClient::cook);
    }

    /**
     * Cooks a lot of burgers
     * @param howMany
     * @return a {@link Flux} having the burgers
     */
    public Flux<Burger> cookBurger(int howMany) {
        Mono<Burger>[] burgerMonos = new Mono[howMany];
        for (int i = 0; i < howMany; i++) {
            burgerMonos[i] = cookBurger();
        }
        return Flux.merge(burgerMonos);
    }

}
