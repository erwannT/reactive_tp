package com.point.tp;

import com.point.tp.client.model.Burger;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Test class to make awesome burgers
 */
public class CookServiceTest {

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

    @Test
    public void should_cook_two_burger_with_two_cook(){

        // Given
        ParallelCookService cookService = new ParallelCookService();

        // When
        List<Burger> burgers = cookService.cookBurger(2);

        // Then
        Assertions.assertThat(burgers).allMatch(burger -> burger.isHot(LocalDateTime.now()));
    }

    @Test
    public void should_cook_six_burger_with_two_cook(){

        // Given
        ParallelCookService cookService = new ParallelCookService();

        // When
        List<Burger> burgers = cookService.cookBurger(6);

        // Then
        Assertions.assertThat(burgers).allMatch(burger -> burger.isHot(LocalDateTime.now()));
    }
}
