package com.juliomesquita.application.usecases.coffee.addproducer;

import com.juliomesquita.application.usecases.coffee.commom.InfoProducer;

public record AddProducerCoffeeInput(Long coffeeId, InfoProducer producer) {
}
