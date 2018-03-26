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

@Slf4j
public class AsyncClient {

    private final WebClient client;
    private final ObjectMapper objectMapper;

    public AsyncClient() {
        Vertx vertx = Vertx.vertx();
        client = WebClient.create(vertx);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void takeCheese(Consumer<Cheese> consumer) {
        callGet("/burger/cheese", Cheese.class, consumer);
    }

    public void takeBacon(Consumer<Bacon> consumer) {
        callGet("/burger/bacon", Bacon.class, consumer);
    }


    public void cookBacon(Bacon bacon, Consumer<Bacon> consumer) {
        callPost("/burger/bacon", Bacon.class, bacon, consumer);
    }

    public void takeBread(Consumer<Bread> consumer) {
        callGet("/burger/bread", Bread.class, consumer);
    }

    public void cutBread(Bread bread, Consumer<Bread> consumer) {
        callPost("/burger/bread", Bread.class, bread, consumer);
    }

    public void takeSalad(Consumer<Salad> consumer) {

        callGet("/burger/salad", Salad.class, consumer);
    }

    public void takeSalsa(Consumer<Salsa> consumer) {

        callGet("/burger/salsa", Salsa.class, consumer);
    }

    public void takeSteak(Consumer<Steak> consumer) {
        callGet("/burger/steak", Steak.class, consumer);
    }


    public void cookSteak(Steak steak,Consumer<Steak> consumer) {
        callPost("/burger/steak", Steak.class, steak, consumer);
    }

    public void takeTomato(Consumer<Tomato> consumer) {
        callGet("/burger/tomato", Tomato.class, consumer);
    }

    public void cook(Burger burger, Consumer<Burger> consumer) {
        callPost("/burger", Burger.class, burger, consumer);

    }

    public <T> void callGet(String requestURI, Class<T> clazz, Consumer<T> consumer) {
        client.get(8080, "localhost", requestURI)
            .send(ar -> {
                manageResponse(clazz, consumer, ar);
            });
    }


    private <T> void callPost(String requestURI, Class<T> clazz, T body, Consumer<T> consumer) {

        client.post(8080, "localhost", requestURI)
            .sendJson(body, ar -> {
                manageResponse(clazz, consumer, ar);
            });
    }

    private <T> void manageResponse(Class<T> clazz, Consumer<T> consumer, AsyncResult<HttpResponse<Buffer>> ar) {
        if (ar.succeeded()) {
            // Obtain response
            HttpResponse<Buffer> response = ar.result();
            try {
                T ressource = objectMapper.readValue(response.bodyAsString(), clazz);
                consumer.accept(ressource);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.error("Une erreur s'est produite : " + ar.cause().getMessage());
        }
    }

}
