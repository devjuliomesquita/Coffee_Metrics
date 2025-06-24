package com.juliomesquita.application.usecases.coffee.commom;

import jakarta.validation.constraints.Positive;

public record InfoProducer(
        String nameProducer, String region, @Positive Integer altitude) {
}
