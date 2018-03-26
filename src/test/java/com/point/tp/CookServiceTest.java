package com.point.tp;

import com.point.tp.client.model.Burger;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    // TODO 4 Comment the @Ignore annotation, the test should fail
    // TODO 6 Run the test again, it should pass
    @Test
    @Ignore
    public void should_cook_burger_with_reactive(){

        // Given
        ReactiveCookService reactiveCookService = new ReactiveCookService();

        // When
        Mono<Burger> burgerMono = reactiveCookService.cookBurger();

        // Then
        Assertions.assertThat(burgerMono.block()).isNotNull();
    }

    // TODO 7 Comment the @Ignore annotation, the test should fail
    // TODO 9 Run the test again, it should pass, see what's happening in your console
    // You might look after 'Final operation : I'm cooking a gorgeous burger' logs
    @Test
    @Ignore
    public void should_cook_many_many_many_many_burger_with_reactive(){

        // Given
        ReactiveCookService reactiveCookService = new ReactiveCookService();

        // When
        Flux<Burger> burgerFlux = reactiveCookService.cookBurger(400);

        // Then
        Iterable<Burger> burgers = burgerFlux.toIterable();
        LocalDateTime now = LocalDateTime.now();
        Assertions.assertThat(burgers).allMatch(burger -> burger.isHot(now));
    }
}
