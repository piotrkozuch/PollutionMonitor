package io.github.piotrkozuch.pm.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static java.time.Instant.now;

public class Utils {

    public static URL url(String url) {
        try {
            return new URL(Checks.checkRequired("url", url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
