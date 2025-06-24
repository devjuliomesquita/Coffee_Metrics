package com.juliomesquita.application.usecases.coffee.addroasting;

import com.juliomesquita.application.usecases.coffee.commom.RoastingResponse;

public record AddRoastingCoffeeInput(Long coffeeId, RoastingResponse roasting) {
}
