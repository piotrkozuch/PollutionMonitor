package io.github.piotrkozuch.pm.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JobExecutionsRepositoryTest implements JobExecutionsTestData {

    @Autowired
    private JobExecutionsRepository repository;

    @Test
    void should_load_job_execution_by_job_name() {
        // given
        var jobExecution = aJobExecution().build();
        repository.save(jobExecution);

        // when
        var result = repository.findByJobName(jobExecution.getJobName());

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(jobExecution.getId());
        assertThat(result.get().getExecutionTime()).isEqualTo(jobExecution.getExecutionTime());
        assertThat(result.get().getJobName()).isEqualTo(jobExecution.getJobName());
    }
}