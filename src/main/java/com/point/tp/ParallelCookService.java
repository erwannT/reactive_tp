package com.point.tp;

import com.point.tp.client.model.Burger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorService;

/**
 * Cook service used to make delightful burgers in parallel.
 */
@Slf4j
public class ParallelCookService {

    /**
     * Initializes a new {@link CookService}.
     */
    private CookService cookService = new CookService();

    /**
     * Creates a pool of threads : the number of cooks you have in the kitchen.
     */
    // TODO 2 Using Executors.newFixedThreadPool create a kitchen with two cooks
    private final ExecutorService kitchen = null;

    /**
     * Cook some juicy burgers.
     *
     * @param howMany burgers you want to cook.
     * @return list of all created burgers
     */
    public List<Burger> cookBurger(int howMany) {

        // Creates an asynchronous service for the kitchen
        // TODO 3 Create an ExecutorCompletionService for the kitchen
        CompletionService<Burger> completionService = null;

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
        // TODO 4 Implement the sendOrder methods
        // You might use submit method
    }

    /**
     * Retrieves the cooked burgers
     * @param howMany
     * @param completionService
     * @return
     */
    private List<Burger> getCookedBurgers(int howMany, CompletionService<Burger> completionService) {
        // TODO 5 implement the getCookedBurgers
        // Must calls the retrieveCookedBurger method
        return null;
    }

    /**
     * Retrieves the next ready burger, if none are ready, wait for one to be.
     *
     * @param completionService
     * @return
     * @throws RuntimeException if something bad happens
     */
    private Burger retrieveCookedBurger(CompletionService<Burger> completionService) {
        //try {
            // TODO 6 retrieve the next ready burger
            // You moght use take, and then get method
            // You will uncomment the try-catch block
            return null;
        //} catch (InterruptedException | ExecutionException e) {
        //    log.error(e.getMessage(), e);
        //    throw new RuntimeException(e);
        //}
    }

}
