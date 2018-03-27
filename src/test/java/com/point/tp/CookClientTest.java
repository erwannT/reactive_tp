package com.point.tp;

import com.point.tp.client.CookClient;
import com.point.tp.client.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for {@link CookClient}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CookClientTest {

    /**
     * Test method for {@link CookClient#cook(Burger)}
     */
    // TODO 2 Run the test, it should pass
    @Test
    public void should_cook_a_burger() {

        // Given
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

        Steak steak = cookClient.takeSteak();
        Assertions.assertThat(steak).isNotNull();

        Steak cookSteak = cookClient.cookSteak(steak);
        Assertions.assertThat(cookSteak).isNotNull();
        Assertions.assertThat(cookSteak.getState()).isEqualTo(SteakState.COOKED);

        Tomato tomato = cookClient.takeTomato();
        Assertions.assertThat(tomato).isNotNull();

        Burger burger = new Burger(cookBacon, cutBread,cheese,salad,salsa,cookSteak,tomato);

        // When
        Burger cookedBurger = cookClient.cook(burger);

        // Then
        Assertions.assertThat(cookedBurger).isNotNull();
    }
}
