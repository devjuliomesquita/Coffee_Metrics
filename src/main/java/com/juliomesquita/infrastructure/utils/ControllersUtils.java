package com.juliomesquita.infrastructure.utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Map;

public final class ControllersUtils {
    private ControllersUtils() {
    }

    public static URI buildUri(final UriInfo uriInfo, final Long id) {
        return uriInfo.getAbsolutePathBuilder()
                .path(id.toString())
                .build();
    }

    public static Response buildResponse(final List<String> messages, final Integer status){
        return Response.status(status)
            .entity(Map.of("errors", messages))
            .build();
    }
}
