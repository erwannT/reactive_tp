package com.point.tp;

import com.point.tp.client.CookClient;
import com.point.tp.client.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link CookClient}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CookClientTest {

    /**
     * Test method for {@link CookClient#cook(Burger)}
     */
    @Test
    public void should_cook_a_burger() {

        // Given
        CookClient cookClient = new CookClient();

        Cheese cheese = cookClient.takeCheese();
        assertThat(cheese).isNotNull();

        Bacon bacon = cookClient.takeBacon();
        assertThat(bacon).isNotNull();

        Bacon cookBacon = cookClient.cookBacon(bacon);
        assertThat(cookBacon).isNotNull();
        assertThat(cookBacon.getState()).isEqualTo(BaconState.COOKED);

        Bread bread = cookClient.takeBread();
        assertThat(bread).isNotNull();
        Bread cutBread = cookClient.cutBread(bread);
        assertThat(cutBread).isNotNull();
        assertThat(cutBread.getState()).isEqualTo(BreadState.CUT);

        Salad salad = cookClient.takeSalad();
        assertThat(salad).isNotNull();

        Salsa salsa = cookClient.takeSalsa();
        assertThat(salsa).isNotNull();

        Steak steak = cookClient.takeSteak();
        assertThat(steak).isNotNull();

        Steak cookSteak = cookClient.cookSteak(steak);
        assertThat(cookSteak).isNotNull();
        assertThat(cookSteak.getState()).isEqualTo(SteakState.COOKED);

        Tomato tomato = cookClient.takeTomato();
        assertThat(tomato).isNotNull();

        Burger burger = new Burger(cookBacon, cutBread,cheese,salad,salsa,cookSteak,tomato);

        // When
        Burger cookedBurger = cookClient.cook(burger);

        // Then
        assertThat(cookedBurger).isNotNull();
    }
}
