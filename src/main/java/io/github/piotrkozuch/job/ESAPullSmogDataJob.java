package io.github.piotrkozuch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

@Component
public class ESAPullSmogDataJob {

    private static final Logger LOG = LoggerFactory.getLogger(ESAPullSmogDataJob.class);
    private static final long INITIAL_DELAY = 1000 * 60;
    private static final long EXECUTION_INTERVAL = 1000 * 60 * 5;

    private final ESASmogDataImporter esaSmogDataImporter;

    @Autowired
    public ESAPullSmogDataJob(ESASmogDataImporter esaSmogDataImporter) {
        this.esaSmogDataImporter = checkRequired("esaSmogDataImporter", esaSmogDataImporter);
    }

    @Scheduled(initialDelay = INITIAL_DELAY, fixedRate = EXECUTION_INTERVAL)
    public void importSmogData() {
        LOG.info("Importing ESA smog data.");
        try {
            esaSmogDataImporter.importSmogData();
            LOG.info("ESA smog data import completed.");
        } catch (RuntimeException e) {
            LOG.error("Smog data import failed!", e);
        }
    }
}
