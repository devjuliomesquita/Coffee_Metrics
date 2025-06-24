package com.juliomesquita.infrastructure.configs;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

import static com.juliomesquita.infrastructure.utils.ControllersUtils.buildResponse;

@Provider
public class ExceptionsHandlerConstraintViolation
    implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(final ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
            .stream()
            .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
            .toList();

        return buildResponse(errors, 400);
    }
}
