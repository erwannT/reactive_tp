package com.point.tp.client.model;

import lombok.Data;

/**
 *
 */
@Data
public class Bread extends Ingredient{
    private BreadState state;

    public Bread() {
        this.state = BreadState.FULL;
    }
}
