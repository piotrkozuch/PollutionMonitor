package io.github.piotrkozuch.integration.esa;

import io.github.piotrkozuch.integration.esa.response.SmogDataPage;

public interface ESAClient {

    SmogDataPage getSmogDataPage(int pageNumber);
}
