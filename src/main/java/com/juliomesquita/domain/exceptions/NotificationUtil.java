package com.juliomesquita.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class NotificationUtil {
    private final List<String> errors;

    public void addError(final String error) {
        errors.add(error);
    }

    public boolean isEmpty() {
        return errors.isEmpty();
    }

    public NotificationUtil() {
        this.errors = new ArrayList<String>();
    }

    public List<String> getErrors() {
        return errors;
    }
}
