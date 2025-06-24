package com.juliomesquita.application.usecases.coffee.update;

import com.juliomesquita.application.usecases.coffee.commom.InfoCoffee;

public record UpdateCoffeeInput(
        Long coffeeId,
        InfoCoffee infoCoffee
) {
}
