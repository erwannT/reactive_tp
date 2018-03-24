package com.point.tp.client.model;

import lombok.Data;

/**
 *
 */
@Data
public class Bacon extends Ingredient {
    private BaconState state;

    /**
     * Initializes a bacon with a random auto-generated UUID.
     */
    public Bacon() {
        this.state = BaconState.RAW;
    }
}

