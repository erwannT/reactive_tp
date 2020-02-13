package com.point.tp;

import com.point.tp.client.model.Burger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BackPressureTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookServiceTest.class);

    @Test
    public void restaurantOnPressure() throws InterruptedException {

        List<Burger> wastedBurgers = new ArrayList<>();
        List<Burger> coldBurgers = new ArrayList<>();
        List<Burger> goodBurgers = new ArrayList<>();

        Flux<Object> burgerGenerator = Flux.create(burgerSink -> new Thread(() -> cookBurgers(burgerSink, 100, 100)).start())
                .publishOn(Schedulers.newElastic("Restaurant"))
                .doOnComplete(() -> LOGGER.info("End of production, no more ingredients :)"));

        burgerGenerator
                //.onBackpressureBuffer(10, wastedBurger->wasteBurger(wastedBurgers, (Burger) wastedBurger), BufferOverflowStrategy.DROP_OLDEST)
                //.onBackpressureDrop(wastedBurger->wasteBurger(wastedBurgers, (Burger) wastedBurger))
                .onBackpressureLatest()
                //.onBackpressureError()
                .subscribeOn(Schedulers.newSingle("Customers"))
                .delayElements(Duration.ofMillis(200))
                .doFinally(signal -> logStats(wastedBurgers, goodBurgers, coldBurgers))
                .subscribe(burger -> consumeABurger((Burger) burger, goodBurgers, coldBurgers));

        Thread.sleep(10000000);
    }

    private void wasteBurger(List<Burger> wastedBurgers, Burger wastedBurger) {
        wastedBurgers.add(wastedBurger);
        LOGGER.info("Burger "+ wastedBurger.getId()+" is wasted");
    }

    private void logStats(List<Burger> wasted, List<Burger> good, List<Burger> cold) {
        LOGGER.info(wasted.size() + " burgers have been wasted :/");
        LOGGER.info(wasted.stream().map(Burger::getId).collect(Collectors.toList()).toString());

        LOGGER.info(cold.size() + " burgers were cold :X");
        LOGGER.info(cold.stream().map(Burger::getId).collect(Collectors.toList()).toString());

        LOGGER.info(good.size() + " burgers were good :D");
        LOGGER.info(good.stream().map(Burger::getId).collect(Collectors.toList()).toString());
    }

    private void consumeABurger(Burger burger, List<Burger> goodBurgers, List<Burger> coldBurgers) {
        LOGGER.info("A customer has requested burger " + burger.getId());
        if (burger.isHot(LocalDateTime.now())) {
            LOGGER.info("Good burger !!!");
            goodBurgers.add(burger);
        } else {
            LOGGER.error("This burger is cold");
            coldBurgers.add(burger);
        }
    }

    private void cookBurgers(FluxSink<Object> synchronousSink, int cookingTime, int howMany) {
        LOGGER.info("Hello, we will cook gorgeous burgers");
        long i = 0;
        while (i < howMany) {
            LOGGER.info("Cook burger " + i);
            synchronousSink.next(cookBurger(++i));
            try {
                Thread.sleep(cookingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronousSink.complete();
    }

    private Burger cookBurger(Long id) {
        Burger burger = new Burger();
        burger.setRelease(LocalDateTime.now());
        burger.setId(id);
        return burger;
    }
}
