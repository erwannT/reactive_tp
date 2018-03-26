package com.point.tp.client;

import com.point.tp.client.model.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cook client used to make delightful burgers.
 * Uses {@link WebClient} object to request a running future-burger api in a reactive way.
 * The future-burger api must run with localhost ip and 8080 port configuration.
 */
public class ReactiveCookClient {

    /**
     * Initializes a new {@link WebClient} to request future-burger api.
     * This client can do both sequential and reactive calls.
     */
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    /**
     * Take a piece of cheese.
     * Will call <future-burger>/burger/cheese endpoint
     *
     * @return the newly created {@link Cheese} object
     */
    public Mono<Cheese> takeCheese() {
        return webClient.get().uri("/burger/cheese").retrieve().bodyToMono(Cheese.class);
    }

    /**
     * Take a piece of bacon.
     * Will call <future-burger>/burger/bacon endpoint
     *
     * @return the newly created {@link Bacon} object
     */
    public Mono<Bacon> takeBacon() {
        return webClient.get().uri("/burger/bacon").retrieve().bodyToMono(Bacon.class);
    }

    /**
     * Cook bacon.
     * Will call POST <future-burger>/burger/bacon endpoint
     *
     * @return the updated {@link Bacon} object
     */
    public Mono<Bacon> cookBacon(Bacon bacon) {
        return webClient.post().uri("/burger/bacon")
                .body(Mono.just(bacon), Bacon.class)
                .retrieve().bodyToMono(Bacon.class);
    }

    /**
     * Take a piece of bread.
     * Will call <future-burger>/burger/bread endpoint
     *
     * @return the newly created {@link Bread} object
     */
    public Mono<Bread> takeBread() {
        return webClient.get().uri("/burger/bread").retrieve().bodyToMono(Bread.class);
    }

    /**
     * Cut bread.
     * Will call POST <future-burger>/burger/bread endpoint
     *
     * @return the updated {@link Bread} object
     */
    public Mono<Bread> cutBread(Bread bread) {
        return webClient.post().uri("/burger/bread")
                .body(Mono.just(bread), Bread.class)
                .retrieve().bodyToMono(Bread.class);
    }

    /**
     * Take some salad.
     * Will call <future-burger>/burger/salad endpoint
     *
     * @return the newly created {@link Salad} object
     */
    public Mono<Salad> takeSalad() {
        return webClient.get().uri("/burger/salad").retrieve().bodyToMono(Salad.class);
    }

    /**
     * Take some salsa.
     * Will call <future-burger>/burger/salsa endpoint
     *
     * @return the newly created {@link Salsa} object
     */
    public Mono<Salsa> takeSalsa() {
        return webClient.get().uri("/burger/salsa").retrieve().bodyToMono(Salsa.class);
    }

    /**
     * Take a steak.
     * Will call <future-burger>/burger/steak endpoint
     *
     * @return the newly created {@link Steak} object
     */
    public Mono<Steak> takeSteak() {
        return webClient.get().uri("/burger/steak").retrieve().bodyToMono(Steak.class);
    }

    /**
     * Cook steak.
     * Will call POST <future-burger>/burger/bacon endpoint
     *
     * @return the updated {@link Steak} object
     */
    public Mono<Steak> cookSteak(Steak steak) {
        return webClient.post().uri("/burger/steak").body(Mono.just(steak), Steak.class).retrieve().bodyToMono(Steak.class);
    }

    /**
     * Take a tomato.
     * Will call <future-burger>/burger/tomato endpoint
     *
     * @return the newly created {@link Tomato} object
     */
    public Mono<Tomato> takeTomato() {
        return webClient.get().uri("/burger/tomato").retrieve().bodyToMono(Tomato.class);
    }

    /**
     * Given all the needed ingredients, make a delightful, tasty, juicy, fat burger.
     * Will call POST <future-burger>/burger endpoint
     *
     * @return the awesome {@link Burger} object
     */
    public Mono<Burger> cook(Burger burger) {
        return webClient.post().uri("/burger").body(Mono.just(burger), Burger.class).retrieve().bodyToMono(Burger.class);
    }
}