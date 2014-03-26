
package org.mule.module.google.spreadsheet.oauth;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface OAuth2Adapter extends OAuthAdapter
{

    /**
     * Build authorization URL and create the inbound endpoint for the callback
     *
     * @param extraParameters Extra query string parameters that should be added to the authorization URL
     * @return The authorization URL
     */
    String authorize(java.util.Map<String, String> extraParameters, String accessTokenUrl, String redirectUri) throws UnableToAcquireRequestTokenException;

    /**
     * Acquire access token and secret
     *
     * @throws UnableToAcquireAccessTokenException
     *
     */
    void fetchAccessToken(String accessTokenUrl, String redirectUri) throws UnableToAcquireAccessTokenException;

    void setExpiration(java.util.Date value);

    boolean hasTokenExpired();

    void refreshAccessToken(String accessTokenUrl) throws UnableToAcquireAccessTokenException;

    String getRefreshToken();
}
