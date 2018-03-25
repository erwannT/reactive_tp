package com.point.tp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

@Slf4j
public class BackPressureTest1 {

    @Test
    public void should_back_pressure() {

        Flux<String> flux = Flux.generate(
            () -> 0,
            (state, sink) -> {
                sink.next("3 x " + state + " = " + 3 * state);
                if (state == 10) sink.complete();
                return state + 1;
            }, (state) -> System.out.println("state: " + state));


        flux.subscribe(System.out::println);

    }

    @Test
    public void test() throws InterruptedException {
        Flux
            .create(stringFluxSink -> new Thread(() -> {
                log.info("Hello world");
                int i = 0;
                while (true) {
                    log.info("Generate : " + i);
                    stringFluxSink.next("Value : " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
//                stringFluxSink.complete();
            }).run(), FluxSink.OverflowStrategy.DROP)

//            .publishOn(Schedulers.newElastic("Publish"))
            .map(String::valueOf)
//            .onBackpressureDrop()

            .subscribeOn(Schedulers.newElastic("Subscribers"))
//            .subscribe(value -> {
//                log.info("Display : " + value);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            })
            .subscribe(new BaseSubscriber<String>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(String value) {
                    log.info("Display : " + value);
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

    @Test
    public void test2(){

        Flux.create(new Consumer<FluxSink<Integer>>() {
            @Override
            public void accept(FluxSink<Integer> fluxSink) {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fluxSink.next(1);
                }

            }
        }).hide()
            .onBackpressureDrop()
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    System.out.println(value);
                    request(1);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            //.subscribe(System.out::println);

    }
}
