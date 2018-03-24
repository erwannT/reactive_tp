package com.point.tp;

import com.point.tp.client.ReactiveCookClient;
import com.point.tp.client.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveCookService {


    public Mono<Burger> cookBurger() {

        ReactiveCookClient reactiveCookClient = new ReactiveCookClient();

        Mono<Bacon> baconMono = reactiveCookClient
            .takeBacon()
            .flatMap(reactiveCookClient::cookBacon);


        Mono<Steak> steakMono = reactiveCookClient
            .takeSteack()
            .flatMap(reactiveCookClient::cookSteak);

        Mono<Bread> breadMono = reactiveCookClient
            .takeBread()
            .flatMap(reactiveCookClient::cutBread);


        return Flux.merge(
            baconMono,
            steakMono,
            breadMono,
            reactiveCookClient.takeCheese(),
            reactiveCookClient.takeSalad(),
            reactiveCookClient.takeSalsa(),
            reactiveCookClient.takeTomato()
        ).
            reduce(new Burger(), (burger, ingredient) -> {
                if (ingredient instanceof Bacon) {
                    burger.setBacon((Bacon) ingredient);
                } else if (ingredient instanceof Bread) {
                    burger.setBread((Bread) ingredient);
                } else if (ingredient instanceof Steak) {
                    burger.setSteak((Steak) ingredient);
                } else if (ingredient instanceof Tomato) {
                    burger.setTomato((Tomato) ingredient);
                } else if (ingredient instanceof Cheese) {
                    burger.setCheese((Cheese) ingredient);
                } else if (ingredient instanceof Salad) {
                    burger.setSalad((Salad) ingredient);
                } else if (ingredient instanceof Salsa) {
                    burger.setSalsa((Salsa) ingredient);
                }
                return burger;
            })
            .flatMap(reactiveCookClient::cook);
    }

    public Flux<Burger> cookBurger(int howMany) {

        Mono<Burger>[] burgerMonos = new Mono[howMany];

        for (int i = 0; i < howMany; i++) {
            burgerMonos[i] = cookBurger();
        }

        return Flux.merge(burgerMonos);



    }

}
