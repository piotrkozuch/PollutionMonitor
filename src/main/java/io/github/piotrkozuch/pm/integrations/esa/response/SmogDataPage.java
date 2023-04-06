package io.github.piotrkozuch.pm.integrations.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static io.github.piotrkozuch.pm.utils.Checks.checkRequired;

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

    public static class SmogDataPageBuilder {

        private List<SmogData> smogData = new ArrayList<>();
        private Boolean hasNextPage;
        private Long pagesTotal;

        private SmogDataPageBuilder() {

        }

        public static SmogDataPageBuilder aSmogDataPage() {
            return new SmogDataPageBuilder();
        }

        public SmogDataPage build() {
            return new SmogDataPage(smogData, hasNextPage, pagesTotal);
        }

        public SmogDataPageBuilder smogData(List<SmogData> smogData) {
            this.smogData = smogData;
            return this;
        }

        public SmogDataPageBuilder hasNextPage(Boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
            return this;
        }

        public SmogDataPageBuilder pagesTotal(Long pagesTotal) {
            this.pagesTotal = pagesTotal;
            return this;
        }
    }

}
