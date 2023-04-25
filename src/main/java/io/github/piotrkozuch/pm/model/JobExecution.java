package io.github.piotrkozuch.pm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

@Entity
@Table(name = "job_executions")
public class JobExecution {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String jobName;

    @Column(nullable = false)
    private Instant executionTime;

    public JobExecution() {

    }

    private JobExecution(JobExecutionBuilder builder) {
        this.id = checkRequired("id", builder.id);
        this.jobName = checkRequired("jobName", builder.jobName);
        this.executionTime = checkRequired("executionTime", builder.executionTime);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Instant getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Instant executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobExecution that = (JobExecution) o;
        return Objects.equals(id, that.id) && Objects.equals(jobName, that.jobName) && Objects.equals(executionTime, that.executionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, executionTime);
    }

    @Override
    public String toString() {
        return "JobExecution{" +
            "id=" + id +
            ", jobName='" + jobName + '\'' +
            ", executionTime=" + executionTime +
            '}';
    }

    public static class JobExecutionBuilder {

        private UUID id;
        private String jobName;
        private Instant executionTime;

        private JobExecutionBuilder() {

        }

        public JobExecution build() {
            return new JobExecution(this);
        }

        public static JobExecutionBuilder aJobExecution() {
            return new JobExecutionBuilder();
        }

        public JobExecutionBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public JobExecutionBuilder jobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        public JobExecutionBuilder executionTime(Instant executionTime) {
            this.executionTime = executionTime;
            return this;
        }
    }
}
