package com.point.tp;

import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Cook service used to make delightful burgers in parallel.
 */
@Slf4j
public class ParalleleCookService {

    /**
     * Initializes a new {@link CookService}.
     */
    private CookService cookService = new CookService();

    /**
     * Creates a pool of threads : the number of cooks you have in the kitchen.
     */
    private final ExecutorService kitchen = Executors.newFixedThreadPool(2);

    /**
     * Cook some juicy burgers.
     *
     * @param howMany burgers you want to cook.
     * @return list of all created burgers
     */
    public List<Burger> cookBurger(int howMany) {

        // Creates an asynchronous service for the kitchen
        CompletionService<Burger> completionService = new ExecutorCompletionService<>(kitchen);

        // Take all orders and submit them to the cooks
        sendOrders(howMany, completionService);

        // Retrieves cooked burger from the kitchen
        List<Burger> burgers = getCookedBurgers(howMany, completionService);

        // Closes the kitchen
        kitchen.shutdown();

        return burgers;
    }

    /**
     * Send orders to the cooks
     *
     * @param howMany
     * @param completionService
     */
    private void sendOrders(int howMany, CompletionService<Burger> completionService) {
        IntStream.range(0, howMany)
                .forEach(i -> completionService.submit(cookService::cookBurger));
    }

    /**
     * Retrieves the cooked burgers
     * @param howMany
     * @param completionService
     * @return
     */
    private List<Burger> getCookedBurgers(int howMany, CompletionService<Burger> completionService) {
        return IntStream.range(0, howMany)
                .mapToObj(i_ -> retrieveCookedBurger(completionService))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the next ready burger, if none are ready, wait for one to be.
     *
     * @param completionService
     * @return
     * @throws RuntimeException if something bad happens
     */
    private Burger retrieveCookedBurger(CompletionService<Burger> completionService) {
        try {
            return completionService.take().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
