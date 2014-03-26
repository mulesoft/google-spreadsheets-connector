
package org.mule.module.google.spreadsheet.process;

import javax.annotation.Generated;


/**
 * ProcessTemplate provides a processing context for message processing.
 * <p/>
 * Examples of processing context can be to provide error handling, transaction state verification,
 * transactional demarcation, connection management, security, etc.
 * @param <T> type of the return value of the processing execution
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface ProcessTemplate<T,O >{

       public T execute(ProcessCallback<T, O> callback, org.mule.api.processor.MessageProcessor messageProcessor, org.mule.api.MuleEvent event) throws Exception;
    public T execute(ProcessCallback<T, O> callback, org.mule.api.routing.filter.Filter filter, org.mule.api.MuleMessage message) throws Exception;
}
