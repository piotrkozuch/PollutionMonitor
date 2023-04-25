package io.github.piotrkozuch.pm.repositorie;

import io.github.piotrkozuch.pm.model.JobExecution;

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
