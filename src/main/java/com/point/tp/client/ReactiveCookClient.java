package com.point.tp.client;

import com.point.tp.client.model.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ReactiveCookClient {

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    public Mono<Cheese> takeCheese() {
        return webClient.get().uri("/burger/cheese").retrieve().bodyToMono(Cheese.class);
    }

    public Mono<Bacon> takeBacon() {
        return webClient.get().uri("/burger/bacon").retrieve().bodyToMono(Bacon.class);
    }

    public Mono<Bacon> cookBacon(Bacon bacon) {
        return webClient.post().uri("/burger/bacon")
            .body(Mono.just(bacon), Bacon.class)
            .retrieve().bodyToMono(Bacon.class);
    }

    public Mono<Bread> takeBread() {
        return webClient.get().uri("/burger/bread").retrieve().bodyToMono(Bread.class);
    }

    public Mono<Bread> cutBread(Bread bread) {
        return webClient.post().uri("/burger/bread")
            .body(Mono.just(bread), Bread.class)
            .retrieve().bodyToMono(Bread.class);
    }

    public Mono<Salad> takeSalad() {
        return webClient.get().uri("/burger/salad").retrieve().bodyToMono(Salad.class);
    }

    public Mono<Salsa> takeSalsa() {
        return webClient.get().uri("/burger/salsa").retrieve().bodyToMono(Salsa.class);
    }

    public Mono<Steak> takeSteack() {
        return webClient.get().uri("/burger/steak").retrieve().bodyToMono(Steak.class);
    }

    public Mono<Steak> cookSteak(Steak steak) {
        return webClient.post().uri("/burger/steak").body(Mono.just(steak), Steak.class).retrieve().bodyToMono(Steak.class);
    }

    public Mono<Tomato> takeTomato() {
        return webClient.get().uri("/burger/tomato").retrieve().bodyToMono(Tomato.class);
    }

    public Mono<Burger> cook(Burger burger) {
        return webClient.post().uri("/burger").body(Mono.just(burger), Burger.class).retrieve().bodyToMono(Burger.class);
    }
}