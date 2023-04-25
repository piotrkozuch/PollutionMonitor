package io.github.piotrkozuch.pm.integration.esa;

import io.github.piotrkozuch.pm.integration.esa.response.SmogDataPage;

public interface ESAClient {

    SmogDataPage getSmogDataPage(int pageNumber);
}
