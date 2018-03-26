package com.point.tp;

import com.point.tp.client.ReactiveCookClient;
import com.point.tp.client.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link ReactiveCookClient}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveCookClientTest {

    /**
     * Test method for {@link ReactiveCookClient#cook(Burger)}
     */
    @Test
    public void should_cook_a_burger() {

        // Given
        ReactiveCookClient client = new ReactiveCookClient();

        Mono<Cheese> monoCheese = client.takeCheese();

        Cheese cheese = monoCheese.block();
        assertThat(cheese).isNotNull();

        Mono<Bacon> baconMono = client.takeBacon();
        Bacon bacon = baconMono.block();
        assertThat(bacon).isNotNull();
        Mono<Bacon> baconMono1 = client.cookBacon(bacon);
        Bacon cookedBacon = baconMono1.block();
        assertThat(cookedBacon).isNotNull();
        assertThat(cookedBacon.getState()).isEqualTo(BaconState.COOKED);

        Mono<Bread> breadMono = client.takeBread();
        Bread bread = breadMono.block();
        assertThat(bread).isNotNull();
        Mono<Bread> breadMono1 = client.cutBread(bread);
        Bread cutBread = breadMono1.block();
        assertThat(cutBread).isNotNull();
        assertThat(cutBread.getState()).isEqualTo(BreadState.CUT);

        Mono<Salad> saladMono = client.takeSalad();
        Salad salad = saladMono.block();
        assertThat(salad).isNotNull();

        Mono<Salsa> salsaMono = client.takeSalsa();
        Salsa salsa = salsaMono.block();
        assertThat(salsa).isNotNull();

        Mono<Tomato> tomatoMono = client.takeTomato();
        Tomato tomato = tomatoMono.block();
        assertThat(tomato).isNotNull();

        Mono<Steak> steakMono = client.takeSteak();
        Steak steak = steakMono.block();
        assertThat(steak).isNotNull();
        Mono<Steak> steakMono1 = client.cookSteak(steak);
        Steak cookSteak = steakMono1.block();
        assertThat(cookSteak).isNotNull();
        assertThat(cookSteak.getState()).isEqualTo(SteakState.COOKED);

        Burger burger = new Burger(cookedBacon,cutBread,cheese,salad,salsa, cookSteak, tomato);

        // When
        Mono<Burger> cook = client.cook(burger);

        // Then
        Burger burger1 = cook.block();
        assertThat(burger1).isNotNull();
    }
}
