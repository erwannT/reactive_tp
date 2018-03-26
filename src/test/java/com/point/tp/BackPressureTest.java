package com.point.tp;

import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Slf4j
public class BackPressureTest {

    @Test
    public void test() throws InterruptedException {
        Flux
            .create(stringFluxSink -> new Thread(() -> {
                log.info("Hello world");
                long i = 0;
                while (true) {

                    log.info("Generate : " + i);
                    stringFluxSink.next(cookBurger(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }).start())

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
            .subscribe(new BaseSubscriber<Burger>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Burger value) {
                    log.info("Display : " + value.getId());
                    if( value.isHot(LocalDateTime.now()))
                    {
                        log.info("Good burger !!!");
                    }
                    else{
                        log.error("This burger is cold");
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    request(1);
                }
            })
        ;

        Thread.sleep(1000000);
    }

    public Burger cookBurger(Long id) {

        Burger burger = new Burger();
        burger.setRelease(LocalDateTime.now());
        burger.setId(id);
        return burger;
    }


}
