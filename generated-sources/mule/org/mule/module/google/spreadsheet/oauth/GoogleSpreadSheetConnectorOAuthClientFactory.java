
package org.mule.module.google.spreadsheet.oauth;

import java.io.Serializable;
import javax.annotation.Generated;
import org.mule.api.store.ObjectStore;
import org.mule.common.security.oauth.OAuthState;
import org.mule.module.google.spreadsheet.adapters.GoogleSpreadSheetConnectorOAuth2Adapter;
import org.mule.security.oauth.BaseOAuthClientFactory;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;

@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-03-26T12:37:34-05:00", comments = "Build M4.1875.17b58a3")
public class GoogleSpreadSheetConnectorOAuthClientFactory
    extends BaseOAuthClientFactory
{

    private GoogleSpreadSheetConnectorOAuthManager oauthManager;

    public GoogleSpreadSheetConnectorOAuthClientFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        super(oauthManager, objectStore);
        this.oauthManager = (GoogleSpreadSheetConnectorOAuthManager) oauthManager;
    }

    @Override
    protected Class<? extends OAuth2Adapter> getAdapterClass() {
        return GoogleSpreadSheetConnectorOAuth2Adapter.class;
    }

    @Override
    protected void setCustomAdapterProperties(OAuth2Adapter adapter, OAuthState state) {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) adapter);
        connector.setScope(oauthManager.getScope());
        connector.setApplicationName(oauthManager.getApplicationName());
    }

    @Override
    protected void setCustomStateProperties(OAuth2Adapter adapter, OAuthState state) {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) adapter);
    }

}
