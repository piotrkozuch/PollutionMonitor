package io.github.piotrkozuch.integration;

import io.github.piotrkozuch.db.model.MeasurementStation;

public interface SmogDataConverter<T> {

    MeasurementStation convert(T data);
}
