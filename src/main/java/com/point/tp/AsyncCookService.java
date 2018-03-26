package com.point.tp;

import com.point.tp.client.AsyncClient;
import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * Cooks a {@link Burger}.
 * In order to cook a burger you need to :
 * <ul>
 * <li>1 : Take a piece of cheese</li>
 * <li>2 : Take a piece of bacon</li>
 * <li>3 : Cook the bacon</li>
 * <li>4 : Take a piece of bread</li>
 * <li>5 : Cut the bread</li>
 * <li>6 : Take some salad</li>
 * <li>7 : Take some salsa</li>
 * <li>8 : Take a steak</li>
 * <li>9 : Cook the steak</li>
 * <li>10 : Take a tomato</li>
 * <li>11 : Make a new burger with all those ingredients</li>
 * <li>12 : Cook the burger</li>
 * </ul>
 */
@Slf4j
public class AsyncCookService {

    /**
     * Cooks a {@link Burger}
     *
     * @return
     * @throws InterruptedException
     */
    public Burger cookBurger(){

        AsyncClient asyncClient = new AsyncClient();

        final Burger burger = new Burger();

        // 12
        Consumer<Burger> cook = b -> {
            if (burger.isComplete()) {
                asyncClient.cook(burger, hotBurger -> {
                    log.info("Value : " + hotBurger);
                    log.info("Burger is ready !!!!!!!!!!!!");
                    burger.setRelease(hotBurger.getRelease());
                });
            }
        };

        // 1
        asyncClient.takeCheese(cheese ->
        {
            log.info("Value : " + cheese);
            burger.setCheese(cheese);
            cook.accept(burger);
        });

        // 2, 3
        asyncClient.takeBacon(
                bacon -> {
                    log.info("Value : " + bacon);
                    asyncClient.cookBacon(bacon, cookedBacon ->
                    {
                        log.info("Value : " + cookedBacon);
                        burger.setBacon(cookedBacon);
                        cook.accept(burger);
                    });
                }
        );

        // 4, 5
        asyncClient.takeBread(bread ->
        {
            log.info("Value : " + bread);
            asyncClient.cutBread(bread, cutBread ->
                    {
                        log.info("Value : " + cutBread);
                        burger.setBread(cutBread);
                        cook.accept(burger);
                    }
            );

        });

        // 6
        asyncClient.takeSalad(salad ->
        {
            log.info("Value : " + salad);
            burger.setSalad(salad);
            cook.accept(burger);
        });

        // 7
        asyncClient.takeSalsa(salsa ->
        {
            log.info("Value : " + salsa);
            burger.setSalsa(salsa);
            cook.accept(burger);
        });

        // 8, 9
        asyncClient.takeSteak(steak ->
        {
            log.info("Value : " + steak);
            asyncClient.cookSteak(steak, cookedSteak -> {
                log.info("Value : " + cookedSteak);
                burger.setSteak(cookedSteak);
                cook.accept(burger);
            });

        });

        // 10
        asyncClient.takeTomato(tomato ->
        {
            log.info("Value : " + tomato);
            burger.setTomato(tomato);
            cook.accept(burger);
        });

        // Wait for the cooks to finish their job
        while(burger.getRelease() == null) {
        }
        return burger;
    }

}
