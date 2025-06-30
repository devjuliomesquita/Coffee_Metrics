package com.juliomesquita.application.usecases.grinding.addconfig;

import com.juliomesquita.application.usecases.grinding.commom.Intervals;

import java.util.List;

public record AddConfigGrindingInput(
    Long grindingId,
    Integer clicks,
    List<Intervals> intervals
) {
}
