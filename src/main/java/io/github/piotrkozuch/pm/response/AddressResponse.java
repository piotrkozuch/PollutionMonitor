package io.github.piotrkozuch.pm.response;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

public record AddressResponse(String city, String postcode, String street) {

    public AddressResponse {
        checkRequired("city", city);
    }
}
