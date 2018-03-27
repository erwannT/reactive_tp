package com.point.tp;

import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class to make awesome burgers
 */
@Slf4j
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

    // TODO 7 Run the test, it should pass, see what's happening in your console
    @Test
    public void should_cook_many_many_many_many_burger_with_reactive() throws InterruptedException{

        // Given
        log.info("We want to cook burgers");
        CountDownLatch latch = new CountDownLatch(1);
        ReactiveCookService reactiveCookService = new ReactiveCookService();
        int numberOfBurgers = 100;

        // When
        Flux<Burger> burgerFlux = reactiveCookService.cookBurger(numberOfBurgers);

        // Then
        List<Burger> burgersReadyToBeServed = new ArrayList<>(numberOfBurgers);
        burgerFlux.subscribe(b -> burgersReadyToBeServed.add(b), System.err::println, () -> {
            log.info("We cooked all delightful burgers");
            latch.countDown();
        });

        assertThat(latch.await(600, TimeUnit.SECONDS)).isTrue();

        log.info(burgersReadyToBeServed.get(0).getRelease().toString());
        log.info(burgersReadyToBeServed.get(numberOfBurgers - 1).getRelease().toString());

        LocalDateTime now = LocalDateTime.now();
        long count = burgersReadyToBeServed.stream().filter(b -> b.isHot(now)).count();

        log.info(count + " burgers chauds");
        log.info(numberOfBurgers - count + " burgers froids");
        assertThat(numberOfBurgers - count)
                .as("Aucun burger ne devrait être froid")
                .isEqualTo(0);
    }
}
