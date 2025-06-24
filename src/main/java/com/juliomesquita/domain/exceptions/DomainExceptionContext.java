package com.juliomesquita.domain.exceptions;

import java.util.List;
import java.util.Objects;

public class DomainExceptionContext extends RuntimeException {
    private final NotificationUtil notification;

    public DomainExceptionContext(final NotificationUtil notification) {
        super("Erros de validação de domínio.");
        this.notification = Objects.requireNonNull(notification);
    }

    public List<String> getErrors() {
        return this.notification.getErrors();
    }
}
