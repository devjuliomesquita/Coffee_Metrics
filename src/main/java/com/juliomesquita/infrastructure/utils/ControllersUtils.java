package com.juliomesquita.infrastructure.utils;

import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

public final class ControllersUtils {
    private ControllersUtils() {
    }

    public static URI buildUri(final UriInfo uriInfo, final Long id) {
        return uriInfo.getAbsolutePathBuilder()
                .path(id.toString())
                .build();
    }
}
