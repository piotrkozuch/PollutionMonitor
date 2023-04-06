package io.github.piotrkozuch.pm.repositories;

import io.github.piotrkozuch.pm.models.JobExecution;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

public interface JobExecutionsTestData {

    default JobExecution.JobExecutionBuilder aJobExecution() {
        return JobExecution.JobExecutionBuilder.aJobExecution()
            .executionTime(now())
            .id(randomUUID())
            .jobName("Job");
    }
}
