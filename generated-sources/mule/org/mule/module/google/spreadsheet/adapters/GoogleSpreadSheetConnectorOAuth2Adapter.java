
package org.mule.module.google.spreadsheet.adapters;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.oauth.NotAuthorizedException;
import org.mule.module.google.spreadsheet.oauth.OAuth2Adapter;
import org.mule.module.google.spreadsheet.oauth.OAuthProcessTemplate;
import org.mule.module.google.spreadsheet.oauth.RestoreAccessTokenCallback;
import org.mule.module.google.spreadsheet.oauth.SaveAccessTokenCallback;
import org.mule.module.google.spreadsheet.oauth.UnableToAcquireAccessTokenException;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;
import org.mule.util.IOUtils;


/**
 * A {@code GoogleSpreadSheetConnectorOAuth2Adapter} is a wrapper around {@link GoogleSpreadSheetConnector } that adds OAuth capabilites to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class GoogleSpreadSheetConnectorOAuth2Adapter
    extends GoogleSpreadSheetConnectorProcessAdapter
    implements MuleContextAware, Initialisable, Startable, Stoppable, OAuth2Adapter
{

    private final static Pattern ACCESS_CODE_PATTERN = Pattern.compile("\"access_token\"[ ]*:[ ]*\"([^\\\"]*)\"");
    private final static Pattern REFRESH_TOKEN_PATTERN = Pattern.compile("\"refresh_token\"[ ]*:[ ]*\"([^\\\"]*)\"");
    private final static Pattern EXPIRATION_TIME_PATTERN = Pattern.compile("\"expires_in\"[ ]*:[ ]*([\\d]*)");
    private MuleContext muleContext;
    private String oauthVerifier;
    private String refreshToken;
    private SaveAccessTokenCallback oauthSaveAccessToken;
    private RestoreAccessTokenCallback oauthRestoreAccessToken;
    public String redirectUri;
    private String authorizationUrl = null;
    private String accessTokenUrl = null;
    private Date expiration;
    private final static Logger LOGGER = Logger.getLogger(GoogleSpreadSheetConnectorOAuth2Adapter.class);

    /**
     * Sets muleContext
     * 
     * @param value Value to set
     */
    public void setMuleContext(MuleContext value) {
        this.muleContext = value;
    }

    /**
     * Retrieves oauthVerifier
     * 
     */
    public String getOauthVerifier() {
        return this.oauthVerifier;
    }

    /**
     * Sets oauthVerifier
     * 
     * @param value Value to set
     */
    public void setOauthVerifier(String value) {
        this.oauthVerifier = value;
    }

    /**
     * Retrieves refreshToken
     * 
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * Sets refreshToken
     * 
     * @param value Value to set
     */
    public void setRefreshToken(String value) {
        this.refreshToken = value;
    }

    /**
     * Retrieves oauthSaveAccessToken
     * 
     */
    public SaveAccessTokenCallback getOauthSaveAccessToken() {
        return this.oauthSaveAccessToken;
    }

    /**
     * Sets oauthSaveAccessToken
     * 
     * @param value Value to set
     */
    public void setOauthSaveAccessToken(SaveAccessTokenCallback value) {
        this.oauthSaveAccessToken = value;
    }

    /**
     * Retrieves oauthRestoreAccessToken
     * 
     */
    public RestoreAccessTokenCallback getOauthRestoreAccessToken() {
        return this.oauthRestoreAccessToken;
    }

    /**
     * Sets oauthRestoreAccessToken
     * 
     * @param value Value to set
     */
    public void setOauthRestoreAccessToken(RestoreAccessTokenCallback value) {
        this.oauthRestoreAccessToken = value;
    }

    /**
     * Sets authorizationUrl
     * 
     * @param value Value to set
     */
    public void setAuthorizationUrl(String value) {
        this.authorizationUrl = value;
    }

    /**
     * Retrieves authorizationUrl
     * 
     */
    public String getAuthorizationUrl() {
        return this.authorizationUrl;
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

    /**
     * Sets expiration
     * 
     * @param value Value to set
     */
    public void setExpiration(Date value) {
        this.expiration = value;
    }

    public void initialise()
        throws InitialisationException
    {
        super.initialise();
    }

    public String authorize(Map<String, String> extraParameters, String authorizationUrl, String redirectUri) {
        StringBuilder urlBuilder = new StringBuilder();
        if (authorizationUrl!= null) {
            urlBuilder.append(authorizationUrl);
        } else {
            urlBuilder.append(this.authorizationUrl);
        }
        urlBuilder.append("?");
        urlBuilder.append("response_type=code&");
        urlBuilder.append("client_id=");
        urlBuilder.append(getConsumerKey());
        urlBuilder.append("&redirect_uri=");
        urlBuilder.append(redirectUri);
        String scope = getScope();
        if (scope!= null) {
            urlBuilder.append("&scope=");
            urlBuilder.append(scope);
        }
        for (String parameter: extraParameters.keySet()) {
            urlBuilder.append("&");
            urlBuilder.append(parameter);
            urlBuilder.append("=");
            urlBuilder.append(extraParameters.get(parameter));
        }
        LOGGER.debug(("Authorization URL has been generated as follows: " + urlBuilder));
        return urlBuilder.toString();
    }

    public boolean restoreAccessToken() {
        if (oauthRestoreAccessToken!= null) {
            if (LOGGER.isDebugEnabled()) {
                StringBuilder messageStringBuilder = new StringBuilder();
                messageStringBuilder.append("Attempting to restore access token...");
                LOGGER.debug(messageStringBuilder.toString());
            }
            try {
                oauthRestoreAccessToken.restoreAccessToken();
                setAccessToken(oauthRestoreAccessToken.getAccessToken());
                if (LOGGER.isDebugEnabled()) {
                    StringBuilder messageStringBuilder = new StringBuilder();
                    messageStringBuilder.append("Access token and secret has been restored successfully ");
                    messageStringBuilder.append("[accessToken = ");
                    messageStringBuilder.append(oauthRestoreAccessToken.getAccessToken());
                    messageStringBuilder.append("] ");
                    LOGGER.debug(messageStringBuilder.toString());
                }
                return true;
            } catch (Exception e) {
                LOGGER.error("Cannot restore access token, an unexpected error occurred", e);
            }
        }
        return false;
    }

    public void fetchAccessToken(String accessTokenUrl, String redirectUri)
        throws UnableToAcquireAccessTokenException
    {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("code=");
            builder.append(URLEncoder.encode(oauthVerifier, "UTF-8"));
            builder.append("&client_id=");
            builder.append(URLEncoder.encode(getConsumerKey(), "UTF-8"));
            builder.append("&client_secret=");
            builder.append(URLEncoder.encode(getConsumerSecret(), "UTF-8"));
            builder.append("&grant_type=");
            builder.append(URLEncoder.encode("authorization_code", "UTF-8"));
            builder.append("&redirect_uri=");
            builder.append(URLEncoder.encode(redirectUri));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        fetchAndExtract(accessTokenUrl, builder.toString());
    }

    public void refreshAccessToken(String accessTokenUrl)
        throws UnableToAcquireAccessTokenException
    {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Trying to refresh access token...");
        }
        if (this.refreshToken == null) {
            throw new IllegalStateException("Cannot refresh access token since refresh token is null");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("grant_type=refresh_token");
        builder.append("&client_id=");
        builder.append(getConsumerKey());
        builder.append("&client_secret=");
        builder.append(getConsumerSecret());
        builder.append("&refresh_token=");
        builder.append(refreshToken);
        builder.append("&scope=");
        try {
            builder.append(URLEncoder.encode(getScope(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        setAccessToken(null);
        fetchAndExtract(accessTokenUrl, builder.toString());
    }

    private void fetchAndExtract(String accessTokenUrl, String requestBodyParam)
        throws UnableToAcquireAccessTokenException
    {
        restoreAccessToken();
        if (getAccessToken() == null) {
            try {
                if (LOGGER.isDebugEnabled()) {
                    StringBuilder messageStringBuilder = new StringBuilder();
                    messageStringBuilder.append("Retrieving access token...");
                    LOGGER.debug(messageStringBuilder.toString());
                }
                HttpURLConnection conn = ((HttpURLConnection) new URL(accessTokenUrl).openConnection());
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                if (LOGGER.isDebugEnabled()) {
                    StringBuilder messageStringBuilder = new StringBuilder();
                    messageStringBuilder.append("Sending request to [");
                    messageStringBuilder.append(accessTokenUrl);
                    messageStringBuilder.append("] using the following as content [");
                    messageStringBuilder.append(requestBodyParam);
                    messageStringBuilder.append("]");
                    LOGGER.debug(messageStringBuilder.toString());
                }
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(requestBodyParam);
                out.close();
                String response = IOUtils.toString(conn.getInputStream());
                if (LOGGER.isDebugEnabled()) {
                    StringBuilder messageStringBuilder = new StringBuilder();
                    messageStringBuilder.append("Received response [");
                    messageStringBuilder.append(response);
                    messageStringBuilder.append("]");
                    LOGGER.debug(messageStringBuilder.toString());
                }
                Matcher matcher = ACCESS_CODE_PATTERN.matcher(response);
                if (matcher.find()&&(matcher.groupCount()>= 1)) {
                    setAccessToken(URLDecoder.decode(matcher.group(1), "UTF-8"));
                    if (LOGGER.isDebugEnabled()) {
                        StringBuilder messageStringBuilder = new StringBuilder();
                        messageStringBuilder.append("Access token retrieved successfully ");
                        messageStringBuilder.append("[accessToken = ");
                        messageStringBuilder.append(getAccessToken());
                        messageStringBuilder.append("] ");
                        LOGGER.debug(messageStringBuilder.toString());
                    }
                    if (oauthSaveAccessToken!= null) {
                        try {
                            oauthSaveAccessToken.saveAccessToken(getAccessToken(), null);
                        } catch (Exception e) {
                            LOGGER.error("Cannot save access token, an unexpected error occurred", e);
                        }
                        if (LOGGER.isDebugEnabled()) {
                            StringBuilder messageStringBuilder = new StringBuilder();
                            messageStringBuilder.append("Attempting to save access token...");
                            messageStringBuilder.append("[accessToken = ");
                            messageStringBuilder.append(getAccessToken());
                            messageStringBuilder.append("] ");
                            LOGGER.debug(messageStringBuilder.toString());
                        }
                    }
                    if (LOGGER.isDebugEnabled()) {
                        StringBuilder messageStringBuilder = new StringBuilder();
                        messageStringBuilder.append("Attempting to extract expiration time using ");
                        messageStringBuilder.append("[expirationPattern = ");
                        messageStringBuilder.append("\"expires_in\"[ ]*:[ ]*([\\d]*)");
                        messageStringBuilder.append("] ");
                        LOGGER.debug(messageStringBuilder.toString());
                    }
                    Matcher expirationMatcher = EXPIRATION_TIME_PATTERN.matcher(response);
                    if (expirationMatcher.find()&&(expirationMatcher.groupCount()>= 1)) {
                        Long expirationSecsAhead = Long.parseLong(expirationMatcher.group(1));
                        expiration = new Date((System.currentTimeMillis()+(expirationSecsAhead* 1000)));
                        if (LOGGER.isDebugEnabled()) {
                            StringBuilder messageStringBuilder = new StringBuilder();
                            messageStringBuilder.append("Token expiration extracted successfully ");
                            messageStringBuilder.append("[expiration = ");
                            messageStringBuilder.append(expiration);
                            messageStringBuilder.append("] ");
                            LOGGER.debug(messageStringBuilder.toString());
                        }
                    } else {
                        if (LOGGER.isDebugEnabled()) {
                            StringBuilder messageStringBuilder = new StringBuilder();
                            messageStringBuilder.append("Token expiration could not be extracted from ");
                            messageStringBuilder.append("[response = ");
                            messageStringBuilder.append(response);
                            messageStringBuilder.append("] ");
                            LOGGER.debug(messageStringBuilder.toString());
                        }
                    }
                    if (LOGGER.isDebugEnabled()) {
                        StringBuilder messageStringBuilder = new StringBuilder();
                        messageStringBuilder.append("Attempting to extract refresh token time using ");
                        messageStringBuilder.append("[refreshTokenPattern = ");
                        messageStringBuilder.append("\"refresh_token\"[ ]*:[ ]*\"([^\\\"]*)\"");
                        messageStringBuilder.append("] ");
                        LOGGER.debug(messageStringBuilder.toString());
                    }
                    Matcher refreshTokenMatcher = REFRESH_TOKEN_PATTERN.matcher(response);
                    if (refreshTokenMatcher.find()&&(refreshTokenMatcher.groupCount()>= 1)) {
                        refreshToken = refreshTokenMatcher.group(1);
                        if (LOGGER.isDebugEnabled()) {
                            StringBuilder messageStringBuilder = new StringBuilder();
                            messageStringBuilder.append("Refresh token extracted successfully ");
                            messageStringBuilder.append("[refresh token = ");
                            messageStringBuilder.append(refreshToken);
                            messageStringBuilder.append("] ");
                            LOGGER.debug(messageStringBuilder.toString());
                        }
                    } else {
                        if (LOGGER.isDebugEnabled()) {
                            StringBuilder messageStringBuilder = new StringBuilder();
                            messageStringBuilder.append("Refresh token could not be extracted from ");
                            messageStringBuilder.append("[response = ");
                            messageStringBuilder.append(response);
                            messageStringBuilder.append("] ");
                            LOGGER.debug(messageStringBuilder.toString());
                        }
                    }
                    postAuth();
                } else {
                    throw new Exception(String.format("OAuth access token could not be extracted from: %s", response));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean hasTokenExpired() {
        return ((expiration!= null)&&expiration.before(new Date()));
    }

    public void reset() {
        expiration = null;
        oauthVerifier = null;
        setAccessToken(null);
    }

    public void hasBeenAuthorized()
        throws NotAuthorizedException
    {
        if (getAccessToken() == null) {
            restoreAccessToken();
            if (getAccessToken() == null) {
                throw new NotAuthorizedException("This connector has not yet been authorized, please authorize by calling \"authorize\".");
            }
        }
    }

    @Override
    public<P >ProcessTemplate<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> getProcessTemplate() {
        return new OAuthProcessTemplate(this);
    }

}
