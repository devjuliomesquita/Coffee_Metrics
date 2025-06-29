package com.juliomesquita.domain.commom.params;

import java.util.HashMap;
import java.util.Map;

public record MapParam(Map<String, Object> params, Map<String, String> operations) {
    public static MapParam create() {
        return new MapParam(new HashMap<>(), new HashMap<>());
    }
}
