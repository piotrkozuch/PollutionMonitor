package io.github.piotrkozuch.db;

import io.github.piotrkozuch.db.model.MeasurementStation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class CustomMeasurementStationRepositoryImpl implements CustomMeasurementStationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<MeasurementStation> findByIdWithMeasurements(UUID id, Instant startDate, Instant endDate) {
        final var query = """
            SELECT ms FROM MeasurementStation ms JOIN FETCH ms.measurements m 
            WHERE ms.id = :id
            AND m.createdDate >= :startDate AND m.createdDate < :endDate
            """;

        final var result = entityManager
            .createQuery(query, MeasurementStation.class)
            .setParameter("id", id)
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .getResultList()
            .stream()
            .findFirst();

        return result;
    }
}
