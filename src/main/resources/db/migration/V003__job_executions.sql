CREATE TABLE job_executions (
    id                          UUID                NOT NULL,
    job_name                    VARCHAR             NOT NULL UNIQUE,
    execution_time              TIMESTAMP           NOT NULL,

    PRIMARY KEY(id)
);

CREATE INDEX IF NOT EXISTS job_executions_indx ON job_executions(job_name);