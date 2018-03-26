package com.point.tp;

import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Slf4j
public class BackPressureTest {

    @Test
    public void backPressureExample() throws InterruptedException {
        Flux
                .create(stringFluxSink -> new Thread(() ->
                        fluxSink(stringFluxSink)
                ).start())
                .publishOn(Schedulers.newElastic("Client"))
                .map(o -> (Burger) o)
//            .onBackpressureDrop(burger -> {
//                System.out.println("Drop " +burger.getId());
//            })
//            .onBackpressureBuffer(4, burger -> {
//                log.info("Overflow sur " + burger.getId());
//            })
                .onBackpressureLatest()
                .subscribeOn(Schedulers.newElastic("Cook"))
                .subscribe(burgerBaseSubscriber());

        Thread.sleep(1000000);
    }

    /**
     * Each second, generate a new burger order
     *
     * @param stringFluxSink
     */
    private void fluxSink(FluxSink<Object> stringFluxSink) {
        try {
            log.info("Hello, we will cook gorgeous burgers");
            long i = 0;
            while (true) {
                log.info("Generate : " + i);
                stringFluxSink.next(cookBurger(i));
                Thread.sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Burger subscriber.
     * Every second, request one burger.
     *
     * @return
     */
    private BaseSubscriber<Burger> burgerBaseSubscriber() {
        return new BaseSubscriber<Burger>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(1);
            }

            @Override
            protected void hookOnNext(Burger value) {
                try {
                    log.info("Display : " + value.getId());
                    if (value.isHot(LocalDateTime.now())) {
                        log.info("Good burger !!!");
                    } else {
                        log.error("This burger is cold");
                    }
                    Thread.sleep(5000);
                    request(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Cook a burger, boilerplate
     *
     * @param id
     * @return
     */
    private Burger cookBurger(Long id) {
        Burger burger = new Burger();
        burger.setRelease(LocalDateTime.now());
        burger.setId(id);
        return burger;
    }


}
