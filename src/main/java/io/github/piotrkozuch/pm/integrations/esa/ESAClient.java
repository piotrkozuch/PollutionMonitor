package io.github.piotrkozuch.pm.integrations.esa;

import io.github.piotrkozuch.pm.integrations.esa.response.SmogDataPage;

public interface ESAClient {

    SmogDataPage getSmogDataPage(int pageNumber);
}
