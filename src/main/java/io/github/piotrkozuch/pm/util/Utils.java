package io.github.piotrkozuch.pm.util;

import java.net.MalformedURLException;
import java.net.URL;

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
