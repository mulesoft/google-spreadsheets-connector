
package org.mule.module.google.spreadsheet.oauth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.store.ObjectStore;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.adapters.GoogleSpreadSheetConnectorOAuth2Adapter;
import org.mule.security.oauth.BaseOAuth2Manager;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;
import org.mule.security.oauth.OnNoTokenPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@code GoogleSpreadSheetConnectorOAuthManager} is a wrapper around {@link GoogleSpreadSheetConnector } that adds access token management capabilities to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:04:10-05:00", comments = "Build master.1915.dd1962d")
public class GoogleSpreadSheetConnectorOAuthManager
    extends BaseOAuth2Manager<OAuth2Adapter>
{

    private static Logger logger = LoggerFactory.getLogger(GoogleSpreadSheetConnectorOAuthManager.class);
    private final static String MODULE_NAME = "Google Spreadsheets";
    private final static String MODULE_VERSION = "2.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.5.0-SNAPSHOT";
    private final static String DEVKIT_BUILD = "master.1915.dd1962d";

    @Override
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Sets consumerKey
     * 
     * @param key to set
     */
    public void setConsumerKey(String value) {
        super.setConsumerKey(value);
    }

    /**
     * Sets consumerSecret
     * 
     * @param secret to set
     */
    public void setConsumerSecret(String value) {
        super.setConsumerSecret(value);
    }

    /**
     * Sets scope
     * 
     * @param scope to set
     */
    public void setScope(String value) {
        super.setScope(value);
    }

    /**
     * Sets applicationName
     * 
     * @param scope to set
     */
    public void setApplicationName(String value) {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        connector.setApplicationName(value);
    }

    /**
     * Retrieves applicationName
     * 
     */
    public String getApplicationName() {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) this.getDefaultUnauthorizedConnector());
        return connector.getApplicationName();
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    @Override
    protected OAuth2Adapter instantiateAdapter() {
        return new GoogleSpreadSheetConnectorOAuth2Adapter(this);
    }

    @Override
    protected KeyedPoolableObjectFactory createPoolFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        return new GoogleSpreadSheetConnectorOAuthClientFactory(oauthManager, objectStore);
    }

    @Override
    protected void setCustomProperties(OAuth2Adapter adapter) {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) adapter);
        connector.setConsumerKey(getConsumerKey());
        connector.setConsumerSecret(getConsumerSecret());
        connector.setScope(getScope());
        connector.setApplicationName(getApplicationName());
    }

    protected void fetchCallbackParameters(OAuth2Adapter adapter, String response) {
        GoogleSpreadSheetConnectorOAuth2Adapter connector = ((GoogleSpreadSheetConnectorOAuth2Adapter) adapter);
        ExpressionManager expressionManager = (muleContext.getExpressionManager());
        MuleMessage muleMessage = new DefaultMuleMessage(response, (muleContext));
    }

    public void setOnNoToken(OnNoTokenPolicy policy) {
        this.getDefaultUnauthorizedConnector().setOnNoTokenPolicy(policy);
    }

    @Override
    protected Set<Class<? extends Exception>> refreshAccessTokenOn() {
        Set<Class<? extends Exception>> types = new HashSet<Class<? extends Exception>>();
        types.add((org.mule.modules.google.oauth.invalidation.OAuthTokenExpiredException.class));
        return types;
    }

}
