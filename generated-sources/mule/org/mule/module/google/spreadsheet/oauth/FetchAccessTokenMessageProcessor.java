
package org.mule.module.google.spreadsheet.oauth;

import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.MessageFactory;
import org.mule.module.google.spreadsheet.adapters.GoogleSpreadSheetConnectorOAuth2Adapter;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class FetchAccessTokenMessageProcessor implements MessageProcessor
{

    public String redirectUri;
    private String accessTokenUrl = null;
    private OAuthManager oauthManager;

    public FetchAccessTokenMessageProcessor(OAuthManager oauthManager) {
        this.oauthManager = oauthManager;
    }

    /**
     * Sets redirectUri
     * 
     * @param value Value to set
     */
    public void setRedirectUri(String value) {
        this.redirectUri = value;
    }

    /**
     * Sets accessTokenUrl
     * 
     * @param value Value to set
     */
    public void setAccessTokenUrl(String value) {
        this.accessTokenUrl = value;
    }

    /**
     * Retrieves accessTokenUrl
     * 
     */
    public String getAccessTokenUrl() {
        return this.accessTokenUrl;
    }

    public MuleEvent process(MuleEvent event)
        throws MuleException
    {
        try {
            GoogleSpreadSheetConnectorOAuth2Adapter oauthAdapter = ((GoogleSpreadSheetConnectorOAuth2Adapter) oauthManager.createAccessToken(((String) event.getMessage().getInvocationProperty("_oauthVerifier"))));
            if (oauthAdapter.getAccessTokenUrl() == null) {
                oauthAdapter.setAccessTokenUrl(accessTokenUrl);
            }
            oauthAdapter.fetchAccessToken(oauthAdapter.getAccessTokenUrl(), redirectUri);
            ((GoogleSpreadSheetConnectorOAuthManager) oauthManager).getAccessTokenPoolFactory().passivateObject(((GoogleSpreadSheetConnectorOAuth2Adapter) oauthAdapter).getAccessTokenId(), oauthAdapter);
            event.getMessage().setInvocationProperty("OAuthAccessTokenId", ((GoogleSpreadSheetConnectorOAuth2Adapter) oauthAdapter).getAccessTokenId());
        } catch (Exception e) {
            throw new MessagingException(MessageFactory.createStaticMessage("Unable to fetch access token"), event, e);
        }
        return event;
    }

}
