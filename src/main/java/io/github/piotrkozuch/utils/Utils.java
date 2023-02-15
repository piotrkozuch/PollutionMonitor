package io.github.piotrkozuch.utils;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

public class Utils {

    public static URL url(String url) {
        try {
            return new URL(checkRequired("url", url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
