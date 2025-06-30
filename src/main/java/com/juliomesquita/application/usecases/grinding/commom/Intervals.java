package com.juliomesquita.application.usecases.grinding.commom;

import com.juliomesquita.domain.entities.grinding.GrindingIntervalVO;

public record Intervals(String categoryName, GrindingIntervalVO interval) {
}
