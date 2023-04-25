package io.github.piotrkozuch.pm.util;

import static java.lang.String.format;

public class Checks {

    public static <T> T checkRequired(String param, T obj) {
        if (obj == null) {
            throw new IllegalArgumentException(format("Param '%s' can't be null!", param));
        }

        return obj;
    }
}
