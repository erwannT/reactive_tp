package com.point.tp.client;

import com.point.tp.client.model.*;
import org.springframework.web.client.RestTemplate;

/**
 * Cook client used to make delightful burgers.
 * Uses {@link RestTemplate} object to request a running future-burger api in a sequential way.
 * The future-burger api must run in localhost ip and 8080 port configuration.
 */
public class CookClient {

    // TODO 1 : Read this class and see what's in there : classic sync calls

    /**
     * future-burger endpoint
     */
    private static final String BURGER_SERVICE = "http://localhost:8080";

    /**
     * Initializes a new {@link RestTemplate} to request future-burger api.
     * This client does sequential calls.
     */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Take a piece of cheese.
     * Will call <future-burger>/burger/cheese endpoint
     *
     * @return the newly created {@link Cheese} object
     */
    public Cheese takeCheese() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/cheese", Cheese.class);
    }

    /**
     * Take a piece of bacon.
     * Will call <future-burger>/burger/bacon endpoint
     *
     * @return the newly created {@link Bacon} object
     */
    public Bacon takeBacon() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/bacon", Bacon.class);
    }

    /**
     * Cook bacon.
     * Will call POST <future-burger>/burger/bacon endpoint
     *
     * @return the updated {@link Bacon} object
     */
    public Bacon cookBacon(Bacon bacon) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/bacon", bacon, Bacon.class);
    }

    /**
     * Take a piece of bread.
     * Will call <future-burger>/burger/bread endpoint
     *
     * @return the newly created {@link Bread} object
     */
    public Bread takeBread() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/bread", Bread.class);
    }

    /**
     * Cut bread.
     * Will call POST <future-burger>/burger/bread endpoint
     *
     * @return the updated {@link Bread} object
     */
    public Bread cutBread(Bread bread) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/bread", bread, Bread.class);
    }

    /**
     * Take some salad.
     * Will call <future-burger>/burger/salad endpoint
     *
     * @return the newly created {@link Salad} object
     */
    public Salad takeSalad() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/salad", Salad.class);
    }

    /**
     * Take some salsa.
     * Will call <future-burger>/burger/salsa endpoint
     *
     * @return the newly created {@link Salsa} object
     */
    public Salsa takeSalsa() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/salsa", Salsa.class);
    }

    /**
     * Take a steak.
     * Will call <future-burger>/burger/steak endpoint
     *
     * @return the newly created {@link Steak} object
     */
    public Steak takeSteak() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/steak", Steak.class);
    }

    /**
     * Cook steak.
     * Will call POST <future-burger>/burger/bacon endpoint
     *
     * @return the updated {@link Steak} object
     */
    public Steak cookSteak(Steak steak) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/steak", steak, Steak.class);
    }

    /**
     * Take a tomato.
     * Will call <future-burger>/burger/tomato endpoint
     *
     * @return the newly created {@link Tomato} object
     */
    public Tomato takeTomato() {
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/tomato", Tomato.class);
    }

    /**
     * Given all the needed ingredients, make a delightful, tasty, juicy, fat burger.
     * Will call POST <future-burger>/burger endpoint
     *
     * @return the awesome {@link Burger} object
     */
    public Burger cook(Burger burger) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger", burger, Burger.class);
    }

}
