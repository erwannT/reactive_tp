package com.point.tp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.point.tp.client.model.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Cook client used to make delightful burgers.
 * Uses {@link WebClient} from {@link Vertx} library to request a running future-burger api in a reactive way.
 * The future-burger api must run with localhost ip and 8080 port configuration.
 */
@Slf4j
public class AsyncClient {

    private final WebClient client;
    private final ObjectMapper objectMapper;

    /**
     * Initializes {@link Vertx} boilerplate objects
     */
    public AsyncClient() {
        Vertx vertx = Vertx.vertx();
        client = WebClient.create(vertx);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Take a piece of cheese.
     * Will call <future-burger>/burger/cheese endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeCheese(Consumer<Cheese> consumer) {
        callGet("/burger/cheese", Cheese.class, consumer);
    }

    /**
     * Take a piece of bacon.
     * Will call <future-burger>/burger/bacon endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeBacon(Consumer<Bacon> consumer) {
        callGet("/burger/bacon", Bacon.class, consumer);
    }

    /**
     * Cook bacon.
     * Will call POST <future-burger>/burger/bacon endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void cookBacon(Bacon bacon, Consumer<Bacon> consumer) {
        callPost("/burger/bacon", Bacon.class, bacon, consumer);
    }

    /**
     * Take a piece of bread.
     * Will call <future-burger>/burger/bread endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeBread(Consumer<Bread> consumer) {
        callGet("/burger/bread", Bread.class, consumer);
    }

    /**
     * Cut bread.
     * Will call POST <future-burger>/burger/bread endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void cutBread(Bread bread, Consumer<Bread> consumer) {
        callPost("/burger/bread", Bread.class, bread, consumer);
    }

    /**
     * Take some salad.
     * Will call <future-burger>/burger/salad endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeSalad(Consumer<Salad> consumer) {
        callGet("/burger/salad", Salad.class, consumer);
    }

    /**
     * Take some salsa.
     * Will call <future-burger>/burger/salsa endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeSalsa(Consumer<Salsa> consumer) {

        callGet("/burger/salsa", Salsa.class, consumer);
    }

    /**
     * Take a steak.
     * Will call <future-burger>/burger/steak endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeSteak(Consumer<Steak> consumer) {
        callGet("/burger/steak", Steak.class, consumer);
    }

    /**
     * Cook steak.
     * Will call POST <future-burger>/burger/bacon endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void cookSteak(Steak steak, Consumer<Steak> consumer) {
        callPost("/burger/steak", Steak.class, steak, consumer);
    }

    /**
     * Take a tomato.
     * Will call <future-burger>/burger/tomato endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void takeTomato(Consumer<Tomato> consumer) {
        callGet("/burger/tomato", Tomato.class, consumer);
    }

    /**
     * Given all the needed ingredients, make a delightful, tasty, juicy, fat burger.
     * Will call POST <future-burger>/burger endpoint
     * Fill the {@link Consumer} with the result of the call
     */
    public void cook(Burger burger, Consumer<Burger> consumer) {
        callPost("/burger", Burger.class, burger, consumer);
    }

    /**
     * Calls a GET using {@link Vertx} on future-burger API
     *
     * @param requestURI
     * @param clazz
     * @param consumer
     * @param <T>
     */
    private <T> void callGet(String requestURI, Class<T> clazz, Consumer<T> consumer) {
        client.get(8080, "localhost", requestURI)
                .send(ar -> manageResponse(clazz, consumer, ar));
    }

    /**
     * Calls a POST using {@link Vertx} on future-burger API
     *
     * @param requestURI
     * @param clazz
     * @param consumer
     * @param <T>
     */
    private <T> void callPost(String requestURI, Class<T> clazz, T body, Consumer<T> consumer) {
        client.post(8080, "localhost", requestURI)
                .sendJson(body, ar -> manageResponse(clazz, consumer, ar));
    }

    /**
     * Manages a response
     *
     * @param clazz
     * @param consumer
     * @param ar
     * @param <T>
     */
    private <T> void manageResponse(Class<T> clazz, Consumer<T> consumer, AsyncResult<HttpResponse<Buffer>> ar) {
        if (ar.succeeded()) {
            // Obtain response
            HttpResponse<Buffer> response = ar.result();
            try {
                consumer.accept(objectMapper.readValue(response.bodyAsString(), clazz));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.error("Une erreur s'est produite : " + ar.cause().getMessage());
        }
    }
}
