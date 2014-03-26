
package org.mule.module.google.spreadsheet.oauth;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.google.spreadsheet.adapters.GoogleSpreadSheetConnectorCapabilitiesAdapter;
import org.mule.module.google.spreadsheet.process.ProcessCallback;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class OAuthProcessTemplate<P >implements ProcessTemplate<P, GoogleSpreadSheetConnectorCapabilitiesAdapter>
{

    private final GoogleSpreadSheetConnectorCapabilitiesAdapter object;

    public OAuthProcessTemplate(GoogleSpreadSheetConnectorCapabilitiesAdapter object) {
        this.object = object;
    }

    public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        if (processCallback.isProtected()) {
            ((OAuthAdapter) object).hasBeenAuthorized();
        }
        return processCallback.process(object);
    }

    public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        if (processCallback.isProtected()) {
            ((OAuthAdapter) object).hasBeenAuthorized();
        }
        return processCallback.process(object);
    }

}
