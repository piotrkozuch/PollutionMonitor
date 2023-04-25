package io.github.piotrkozuch.pm.repositorie;

import io.github.piotrkozuch.pm.model.JobExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobExecutionsRepository extends JpaRepository<JobExecution, UUID> {

    Optional<JobExecution> findByJobName(String jobName);
}
