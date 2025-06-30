package com.juliomesquita.application.usecases.grinding.commom;

import com.juliomesquita.domain.entities.grinding.GrindingConfigEntity;
import com.juliomesquita.domain.entities.grinding.GrindingIntervalVO;

import java.util.Map;

public record InfoConfig(Long id, Integer clicks, Map<String, GrindingIntervalVO> intervals) {
    public static InfoConfig with(final GrindingConfigEntity config) {
        return new InfoConfig(config.getId(), config.getClicks(), config.getIntervals());
    }
}
