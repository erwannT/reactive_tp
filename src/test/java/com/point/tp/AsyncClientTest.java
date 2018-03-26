package com.point.tp;

import com.point.tp.client.AsyncClient;
import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Consumer;

@Slf4j
public class AsyncClientTest {

    @Test
    public void should_cook_a_burger() throws InterruptedException {

        AsyncClient asyncClient = new AsyncClient();

        final Burger burger = new Burger();

        Consumer<Burger> cook = b -> {
            if (burger.isComplete()) {
                asyncClient.cook(burger, hotBurger -> {
                    log.info("Value : " + hotBurger);
                    log.info("Burger is ready !!!!!!!!!!!!");
                });
            }
        };

        asyncClient.takeCheese(cheese ->
        {
            log.info("Value : " + cheese);
            burger.setCheese(cheese);
            cook.accept(burger);
        });

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

        asyncClient.takeSalad(salad ->
        {
            log.info("Value : " + salad);
            burger.setSalad(salad);
            cook.accept(burger);
        });

        asyncClient.takeSalsa(salsa ->
        {
            log.info("Value : " + salsa);
            burger.setSalsa(salsa);
            cook.accept(burger);
        });

        asyncClient.takeSteak(steak ->
        {
            log.info("Value : " + steak);
            asyncClient.cookSteak(steak, cookedSteak -> {
                log.info("Value : " + cookedSteak);
                burger.setSteak(cookedSteak);
                cook.accept(burger);
            });

        });

        asyncClient.takeTomato(tomato ->
        {
            log.info("Value : " + tomato);
            burger.setTomato(tomato);
            cook.accept(burger);
        });


        Thread.sleep(10000);


    }
}
