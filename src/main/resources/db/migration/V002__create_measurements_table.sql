CREATE TABLE measurements (
    id                          UUID                NOT NULL,
    measurement_station_id      UUID                NOT NULL,
    humidity_avg                DOUBLE PRECISION,
    pressure_avg                DOUBLE PRECISION,
    temperature_avg             DOUBLE PRECISION,
    pm10_avg                    DOUBLE PRECISION,
    pm25_avg                    DOUBLE PRECISION,
    created_date                TIMESTAMP           NOT NULL,

    PRIMARY KEY(id, created_date),

    CONSTRAINT fk_measurements_station
    FOREIGN KEY (measurement_station_id)
    REFERENCES measurement_stations(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS measurement_station_id_indx ON measurements(measurement_station_id);