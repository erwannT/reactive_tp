package com.point.tp;

import com.point.tp.client.model.Burger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParalleleCookService {


    private CookService cookService = new CookService();

    private final ExecutorService kitchen = Executors.newFixedThreadPool(2);

    public List<Burger> cookBurger(int howMany) throws InterruptedException, ExecutionException {

        List<Burger> burgers = new ArrayList<>();

        CompletionService<Burger> completionService = new ExecutorCompletionService<>(kitchen);


        for (int i = 0; i < howMany; i++) {
            completionService.submit(cookService::cookBurger);
        }


        for (int i = 0; i < howMany; i++) {
            burgers.add(completionService.take().get());
        }


        kitchen.shutdown();


        return burgers;
    }

}
