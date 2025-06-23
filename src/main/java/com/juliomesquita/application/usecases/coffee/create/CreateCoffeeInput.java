package com.juliomesquita.application.usecases.coffee.create;

import com.juliomesquita.application.usecases.coffee.commom.InfoCoffee;
import com.juliomesquita.application.usecases.coffee.commom.InfoProducer;
import com.juliomesquita.application.usecases.coffee.commom.InfoRoasting;

public record CreateCoffeeInput(
        InfoCoffee infoCoffee, InfoProducer infoProducer, InfoRoasting infoRoasting
) {
}
