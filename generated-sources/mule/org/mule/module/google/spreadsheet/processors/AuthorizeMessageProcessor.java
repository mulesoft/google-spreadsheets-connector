
package org.mule.module.google.spreadsheet.processors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.mule.api.DefaultMuleException;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.callback.HttpCallback;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.processor.InterceptingMessageProcessor;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.module.google.spreadsheet.oauth.ExtractAuthorizationCodeMessageProcessor;
import org.mule.module.google.spreadsheet.oauth.FetchAccessTokenMessageProcessor;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthManager;
import org.mule.module.google.spreadsheet.process.DefaultHttpCallback;
import org.mule.modules.google.AccessType;
import org.mule.modules.google.ForcePrompt;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class AuthorizeMessageProcessor
    extends AbstractMessageProcessor<GoogleSpreadSheetConnectorOAuthManager>
    implements FlowConstructAware, MuleContextAware, Initialisable, Startable, Stoppable, InterceptingMessageProcessor
{

    private MessageProcessor listener;
    private String authorizationUrl = null;
    private String accessTokenUrl = null;
    private HttpCallback oauthCallback;
    private final static Pattern AUTH_CODE_PATTERN = Pattern.compile("code=([^&]+)");
    private String state;
    private Object access_type;
    private AccessType _access_typeType;
    private Object force_prompt;
    private ForcePrompt _force_promptType;

    /**
     * Sets listener
     * 
     * @param value Value to set
     */
    public void setListener(MessageProcessor value) {
        this.listener = value;
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
     * Sets state
     * 
     * @param value Value to set
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Sets access_type
     * 
     * @param value Value to set
     */
    public void setAccess_type(Object value) {
        this.access_type = value;
    }

    /**
     * Sets force_prompt
     * 
     * @param value Value to set
     */
    public void setForce_prompt(Object value) {
        this.force_prompt = value;
    }

    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
        GoogleSpreadSheetConnectorOAuthManager moduleObject = null;
        try {
            moduleObject = findOrCreate(GoogleSpreadSheetConnectorOAuthManager.class, false, null);
        } catch (IllegalAccessException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        } catch (InstantiationException e) {
            throw new DefaultMuleException(CoreMessages.failedToStart("authorize"), e);
        }
        if (oauthCallback == null) {
            FetchAccessTokenMessageProcessor fetchAccessTokenMessageProcessor = new FetchAccessTokenMessageProcessor(moduleObject);
            oauthCallback = new DefaultHttpCallback(Arrays.asList(new ExtractAuthorizationCodeMessageProcessor(AUTH_CODE_PATTERN), fetchAccessTokenMessageProcessor, listener), getMuleContext(), moduleObject.getDomain(), moduleObject.getLocalPort(), moduleObject.getRemotePort(), moduleObject.getPath(), moduleObject.getAsync(), getFlowConstruct().getExceptionListener(), moduleObject.getConnector());
            fetchAccessTokenMessageProcessor.setRedirectUri(oauthCallback.getUrl());
            if (accessTokenUrl!= null) {
                fetchAccessTokenMessageProcessor.setAccessTokenUrl(accessTokenUrl);
            } else {
                fetchAccessTokenMessageProcessor.setAccessTokenUrl(moduleObject.getAccessTokenUrl());
            }
            oauthCallback.start();
        }
    }

    public void stop()
        throws MuleException
    {
        if (oauthCallback!= null) {
            oauthCallback.stop();
        }
    }

    /**
     * Starts the OAuth authorization process
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(MuleEvent event)
        throws MuleException
    {
        GoogleSpreadSheetConnectorOAuthManager moduleObject = null;
        try {
            moduleObject = findOrCreate(GoogleSpreadSheetConnectorOAuthManager.class, false, null);
            Map<String, String> extraParameters = new HashMap<String, String>();
            if (state!= null) {
                try {
                    String transformerState = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("state").getGenericType(), null, state));
                    extraParameters.put("state", transformerState);
                } catch (NoSuchFieldException e) {
                    throw new MessagingException(CoreMessages.createStaticMessage("internal error"), event, e);
                }
            }
            if (access_type!= null) {
                try {
                    Object first = evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("_access_typeType").getGenericType(), null, access_type);
                    String second = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("state").getGenericType(), null, first));
                    extraParameters.put("access_type", second.toLowerCase());
                } catch (NoSuchFieldException e) {
                    throw new MessagingException(CoreMessages.createStaticMessage("internal error"), event, e);
                }
            }
            if (force_prompt!= null) {
                try {
                    Object first = evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("_force_promptType").getGenericType(), null, force_prompt);
                    String second = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("state").getGenericType(), null, first));
                    extraParameters.put("force_prompt", second.toLowerCase());
                } catch (NoSuchFieldException e) {
                    throw new MessagingException(CoreMessages.createStaticMessage("internal error"), event, e);
                }
            }
            String transformedAuthorizationUrl = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("authorizationUrl").getGenericType(), null, authorizationUrl));
            String transformedAccessTokenUrl = ((String) evaluateAndTransform(getMuleContext(), event, AuthorizeMessageProcessor.class.getDeclaredField("accessTokenUrl").getGenericType(), null, accessTokenUrl));
            moduleObject.setAccessTokenUrl(transformedAccessTokenUrl);
            String location = moduleObject.authorize(extraParameters, transformedAuthorizationUrl, oauthCallback.getUrl());
            event.getMessage().setOutboundProperty("http.status", "302");
            event.getMessage().setOutboundProperty("Location", location);
            return event;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("authorize"), event, e);
        }
    }

}
