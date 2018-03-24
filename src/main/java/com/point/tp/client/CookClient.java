package com.point.tp.client;

import com.point.tp.client.model.*;
import org.springframework.web.client.RestTemplate;

public class CookClient {

    private static final String BURGER_SERVICE = "http://127.0.0.1:8080";

    private RestTemplate restTemplate = new RestTemplate();

    public Cheese takeCheese(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/cheese", Cheese.class);
    }

    public Bacon takeBacon(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/bacon", Bacon.class);
    }

    public Bacon cookBacon(Bacon bacon){
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/bacon", bacon, Bacon.class);
    }

    public Bread takeBread(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/bread", Bread.class);
    }

    public Bread cutBread(Bread bread) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/bread", bread, Bread.class);
    }

    public Salad takeSalad(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/salad", Salad.class);
    }

    public Salsa takeSalsa(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/salsa", Salsa.class);
    }

    public Steak takeSteack(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/steak", Steak.class);
    }

    public Steak cookSteak(Steak steak){
        return restTemplate.postForObject(BURGER_SERVICE + "/burger/steak", steak, Steak.class);
    }

    public Tomato takeTomato(){
        return restTemplate.getForObject(BURGER_SERVICE + "/burger/tomato", Tomato.class);
    }

    public Burger cook(Burger burger) {
        return restTemplate.postForObject(BURGER_SERVICE + "/burger", burger, Burger.class);
    }

}
