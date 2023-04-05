package io.github.piotrkozuch.job;

import io.github.piotrkozuch.action.StoreMeasurementsAction;
import io.github.piotrkozuch.db.model.MeasurementStation;
import io.github.piotrkozuch.integration.esa.ESAClient;
import io.github.piotrkozuch.integration.esa.ESASmogDataConverter;
import io.github.piotrkozuch.integration.esa.response.SmogDataPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

@Component
public class ESASmogDataImporter {

    private final ESAClient esaClient;
    private final StoreMeasurementsAction storeMeasurementsAction;
    private final ESASmogDataConverter esaSmogDataConverter;

    @Autowired
    public ESASmogDataImporter(ESAClient esaClient,
                               ESASmogDataConverter esaSmogDataConverter,
                               StoreMeasurementsAction storeMeasurementsAction) {
        this.esaClient = checkRequired("esaClient", esaClient);
        this.esaSmogDataConverter = checkRequired("esaSmogDataConverter", esaSmogDataConverter);
        this.storeMeasurementsAction = checkRequired("storeMeasurementsAction", storeMeasurementsAction);
    }

    public void importSmogData() {
        iterateOverAllSmogDataPages(smogDataPage ->
            smogDataPage.smogData()
                .forEach(smogData -> save(esaSmogDataConverter.convert(smogData)))
        );
    }

    private void save(MeasurementStation measurementStation) {
        storeMeasurementsAction.execute(new StoreMeasurementsAction.Params(measurementStation));
    }

    private void iterateOverAllSmogDataPages(Consumer<SmogDataPage> smogDataPageConsumer) {
        var pageNumber = -1;
        while (true) {
            final var smogDataPage = esaClient.getSmogDataPage(++pageNumber);

            smogDataPageConsumer.accept(smogDataPage);

            if (!smogDataPage.hasNextPage()) {
                break;
            }
        }
    }
}
