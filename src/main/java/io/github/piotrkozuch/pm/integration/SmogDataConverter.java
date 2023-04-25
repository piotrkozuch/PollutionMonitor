package io.github.piotrkozuch.pm.integration;

import io.github.piotrkozuch.pm.model.MeasurementStation;

public interface SmogDataConverter<T> {

    MeasurementStation convert(T data);
}
