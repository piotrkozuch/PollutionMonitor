CREATE TABLE measurement_stations (
    id              UUID                PRIMARY KEY,
    name            VARCHAR(300)        NOT NULL UNIQUE,
    street          VARCHAR(200),
    postcode        VARCHAR(10),
    city            VARCHAR(200)        NOT NULL,
    longitude       DOUBLE PRECISION    NOT NULL,
    latitude        DOUBLE PRECISION    NOT NULL,
    created_date    TIMESTAMP           NOT NULL,
    updated_date    TIMESTAMP           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS measurement_stations_name_indx ON measurement_stations(name);
CREATE INDEX IF NOT EXISTS measurement_stations_city_indx ON measurement_stations(city);