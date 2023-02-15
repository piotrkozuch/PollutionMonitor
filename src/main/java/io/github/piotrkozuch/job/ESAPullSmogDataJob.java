package io.github.piotrkozuch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ESAPullSmogDataJob {

    private static final Logger LOG = LoggerFactory.getLogger(ESAPullSmogDataJob.class);

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        LOG.info("The time is now {}", LocalDateTime.now());
    }
}
