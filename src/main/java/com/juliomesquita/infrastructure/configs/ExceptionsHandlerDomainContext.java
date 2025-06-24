package com.juliomesquita.infrastructure.configs;

import com.juliomesquita.domain.exceptions.DomainExceptionContext;
import com.juliomesquita.infrastructure.utils.ControllersUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionsHandlerDomainContext implements ExceptionMapper<DomainExceptionContext> {
    @Override
    public Response toResponse(final DomainExceptionContext e) {
        return ControllersUtils.buildResponse(e.getErrors(), 422);
    }
}
