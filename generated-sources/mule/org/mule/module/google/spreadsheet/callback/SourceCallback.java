
package org.mule.module.google.spreadsheet.callback;

import javax.annotation.Generated;


/**
 * Callback interface used by {@link org.mule.api.annotations.Source} annotated methods to dispatch messages.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface SourceCallback {

        /**
     * Dispatch the current event to the flow
     *
     * @return The response of the flow
     */
    Object process() throws Exception;

    /**
     * Dispatch message to the flow
     *
     * @param payload The payload of the message
     * @return The response of the flow
     */
    Object process(Object payload) throws Exception;

    /**
     * Dispatch message to the flow with properties
     *
     * @param payload    The payload of the message
     * @param properties Properties to be attached with inbound scope
     * @return The response of the flow
     */
    Object process(Object payload, java.util.Map<String, Object> properties) throws Exception;

    /**
     * Dispatch the current event to the flow
     *
     * @return The response of the flow
     */
    org.mule.api.MuleEvent processEvent(org.mule.api.MuleEvent event) throws org.mule.api.MuleException;
}
