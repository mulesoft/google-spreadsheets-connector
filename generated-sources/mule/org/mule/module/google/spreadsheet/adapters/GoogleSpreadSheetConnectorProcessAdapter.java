
package org.mule.module.google.spreadsheet.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.process.ProcessAdapter;
import org.mule.module.google.spreadsheet.process.ProcessCallback;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;


/**
 * A <code>GoogleSpreadSheetConnectorProcessAdapter</code> is a wrapper around {@link GoogleSpreadSheetConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class GoogleSpreadSheetConnectorProcessAdapter
    extends GoogleSpreadSheetConnectorLifecycleAdapter
    implements ProcessAdapter<GoogleSpreadSheetConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> getProcessTemplate() {
        final GoogleSpreadSheetConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,GoogleSpreadSheetConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, GoogleSpreadSheetConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
