package io.github.piotrkozuch.integration.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

public record SmogDataPage(List<SmogData> smogData,
                           Boolean hasNextPage,
                           Long pagesTotal) {

    @JsonCreator
    public SmogDataPage(@JsonProperty("smog_data") List<SmogData> smogData,
                        @JsonProperty("it_has_next_page") Boolean hasNextPage,
                        @JsonProperty("pages_total") Long pagesTotal) {
        this.smogData = checkRequired("smogData", smogData);
        this.hasNextPage = checkRequired("hasNextPage", hasNextPage);
        this.pagesTotal = checkRequired("pagesTotal", pagesTotal);
    }
}
