package com.point.tp;

import com.point.tp.client.model.Burger;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Test class to make awesome burgers
 */
public class CookServiceTest {

    // TODO 3 Comment the @Ignore annotation, the test should fail
    // TODO 5 Run the test again, it should pass
    @Test
    public void should_cook_a_burger() {

        // Given
        CookService cookService = new CookService();

        // When
        Burger burger = cookService.cookBurger();

        // Then
        LocalDateTime now = LocalDateTime.now();

        Assertions.assertThat(burger).isNotNull();
        Assertions.assertThat(burger.getRelease()).isBefore(now);
        Assertions.assertThat(burger.getRelease()).isAfter((now.minus(3, ChronoUnit.SECONDS)));
    }

    // TODO 6 Comment the @Ignore annotation, and see what's happening
    @Test
    public void should_cook_two_burger_with_a_cook() {

        // Given
        CookService cookService = new CookService();

        // When
        Burger burger = cookService.cookBurger();
        Burger burger2 = cookService.cookBurger();

        // Then
        LocalDateTime now = LocalDateTime.now();

        Assertions.assertThat(burger2).isNotNull();
        Assertions.assertThat(burger2.isHot(now)).isTrue();

        Assertions.assertThat(burger).isNotNull();
        Assertions.assertThat(burger.isHot(now)).isTrue();
    }
}
