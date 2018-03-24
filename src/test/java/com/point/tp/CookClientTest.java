package com.point.tp;

import com.point.tp.client.CookClient;
import com.point.tp.client.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CookClientTest {

    @Test
    public void should_cook_a_burger() {

        CookClient cookClient = new CookClient();

        Cheese cheese = cookClient.takeCheese();
        Assertions.assertThat(cheese).isNotNull();


        Bacon bacon = cookClient.takeBacon();
        Assertions.assertThat(bacon).isNotNull();


        Bacon cookBacon = cookClient.cookBacon(bacon);
        Assertions.assertThat(cookBacon).isNotNull();
        Assertions.assertThat(cookBacon.getState()).isEqualTo(BaconState.COOKED);


        Bread bread = cookClient.takeBread();
        Assertions.assertThat(bread).isNotNull();
        Bread cutBread = cookClient.cutBread(bread);
        Assertions.assertThat(cutBread).isNotNull();
        Assertions.assertThat(cutBread.getState()).isEqualTo(BreadState.CUT);



        Salad salad = cookClient.takeSalad();
        Assertions.assertThat(salad).isNotNull();


        Salsa salsa = cookClient.takeSalsa();
        Assertions.assertThat(salsa).isNotNull();


        Steak steack = cookClient.takeSteack();
        Assertions.assertThat(steack).isNotNull();


        Steak cookSteak = cookClient.cookSteak(steack);
        Assertions.assertThat(cookSteak).isNotNull();
        Assertions.assertThat(cookSteak.getState()).isEqualTo(SteakState.COOKED);


        Tomato tomato = cookClient.takeTomato();
        Assertions.assertThat(tomato).isNotNull();

        Burger burger = new Burger();
        burger.setBacon(cookBacon);
        burger.setBread(cutBread);
        burger.setCheese(cheese);
        burger.setSalad(salad);
        burger.setSalsa(salsa);
        burger.setSteak(cookSteak);
        burger.setTomato(tomato);

        Burger cookedBurger = cookClient.cook(burger);

        Assertions.assertThat(cookedBurger).isNotNull();

    }

}
