package io.github.piotrkozuch.pm.integrations;

import io.github.piotrkozuch.pm.models.MeasurementStation;

public interface SmogDataConverter<T> {

    MeasurementStation convert(T data);
}
