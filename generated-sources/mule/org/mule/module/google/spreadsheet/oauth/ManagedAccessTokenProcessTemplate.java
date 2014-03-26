
package org.mule.module.google.spreadsheet.oauth;

import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.google.spreadsheet.adapters.GoogleSpreadSheetConnectorOAuth2Adapter;
import org.mule.module.google.spreadsheet.process.ProcessCallback;
import org.mule.module.google.spreadsheet.process.ProcessCallbackProcessInterceptor;
import org.mule.module.google.spreadsheet.process.ProcessInterceptor;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class ManagedAccessTokenProcessTemplate<P >implements ProcessTemplate<P, GoogleSpreadSheetConnectorOAuth2Adapter>
{

    private final ProcessInterceptor<P, GoogleSpreadSheetConnectorOAuth2Adapter> processInterceptor;

    public ManagedAccessTokenProcessTemplate(OAuthManager<GoogleSpreadSheetConnectorOAuth2Adapter> oauthManager, MuleContext muleContext) {
        ProcessInterceptor<P, GoogleSpreadSheetConnectorOAuth2Adapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, GoogleSpreadSheetConnectorOAuth2Adapter>();
        ProcessInterceptor<P, GoogleSpreadSheetConnectorOAuth2Adapter> refreshTokenProcessInterceptor = new RefreshTokenProcessInterceptor<P>(processCallbackProcessInterceptor);
        ProcessInterceptor<P, GoogleSpreadSheetConnectorOAuth2Adapter> managedAccessTokenProcessInterceptor = new ManagedAccessTokenProcessInterceptor<P>(refreshTokenProcessInterceptor, oauthManager, muleContext);
        processInterceptor = managedAccessTokenProcessInterceptor;
    }

    public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorOAuth2Adapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorOAuth2Adapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}
