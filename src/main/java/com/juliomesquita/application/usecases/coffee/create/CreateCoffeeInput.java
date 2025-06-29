package com.juliomesquita.application.usecases.coffee.create;

import com.juliomesquita.application.usecases.coffee.commom.InfoCoffee;
import com.juliomesquita.application.usecases.coffee.commom.InfoProducer;
import com.juliomesquita.application.usecases.coffee.commom.InfoRoasting;
import jakarta.validation.Valid;

public record CreateCoffeeInput(
    @Valid InfoCoffee infoCoffee, @Valid InfoProducer infoProducer, @Valid InfoRoasting infoRoasting
) {
}
