package io.github.piotrkozuch.pm.integration.esa;

import io.github.piotrkozuch.pm.action.StoreMeasurementsAction;
import io.github.piotrkozuch.pm.integration.esa.response.SmogDataPage;
import io.github.piotrkozuch.pm.model.MeasurementStation;
import io.github.piotrkozuch.pm.util.Checks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ESASmogDataImporter {

    private final ESAClient esaClient;
    private final StoreMeasurementsAction storeMeasurementsAction;
    private final ESASmogDataConverter esaSmogDataConverter;

    @Autowired
    public ESASmogDataImporter(ESAClient esaClient,
                               ESASmogDataConverter esaSmogDataConverter,
                               StoreMeasurementsAction storeMeasurementsAction) {
        this.esaClient = Checks.checkRequired("esaClient", esaClient);
        this.esaSmogDataConverter = Checks.checkRequired("esaSmogDataConverter", esaSmogDataConverter);
        this.storeMeasurementsAction = Checks.checkRequired("storeMeasurementsAction", storeMeasurementsAction);
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
