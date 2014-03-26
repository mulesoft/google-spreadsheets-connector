
package org.mule.module.google.spreadsheet.process;

import java.util.Map;
import javax.annotation.Generated;
import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.NestedProcessor;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class NestedProcessorChain implements NestedProcessor, MuleContextAware
{

    /**
     * Mule Context
     * 
     */
    private MuleContext muleContext;
    /**
     * Chain that will be executed upon calling process
     * 
     */
    private MessageProcessor chain;
    /**
     * Event that will be cloned for dispatching
     * 
     */
    private MuleEvent event;

    public NestedProcessorChain(MuleEvent event, MuleContext muleContext, MessageProcessor chain) {
        this.event = event;
        this.chain = chain;
        this.muleContext = muleContext;
    }

    /**
     * Sets muleContext
     * 
     * @param value Value to set
     */
    public void setMuleContext(MuleContext value) {
        this.muleContext = value;
    }

    /**
     * Sets chain
     * 
     * @param value Value to set
     */
    public void setChain(MessageProcessor value) {
        this.chain = value;
    }

    /**
     * Sets event
     * 
     * @param value Value to set
     */
    public void setEvent(MuleEvent value) {
        this.event = value;
    }

    public Object process()
        throws Exception
    {
        MuleEvent muleEvent;
        muleEvent = new DefaultMuleEvent(event.getMessage(), event);
        return chain.process(muleEvent).getMessage().getPayload();
    }

    public Object process(Object payload)
        throws Exception
    {
        MuleMessage muleMessage;
        muleMessage = new DefaultMuleMessage(payload, muleContext);
        MuleEvent muleEvent;
        muleEvent = new DefaultMuleEvent(muleMessage, event);
        return chain.process(muleEvent).getMessage().getPayload();
    }

    public Object processWithExtraProperties(Map<String, Object> properties)
        throws Exception
    {
        MuleMessage muleMessage;
        muleMessage = event.getMessage();
        for (String property: properties.keySet()) {
            muleMessage.setInvocationProperty(property, properties.get(property));
        }
        MuleEvent muleEvent;
        muleEvent = new DefaultMuleEvent(muleMessage, event);
        return chain.process(muleEvent).getMessage().getPayload();
    }

    public Object process(Object payload, Map<String, Object> properties)
        throws Exception
    {
        MuleMessage muleMessage;
        muleMessage = new DefaultMuleMessage(payload, muleContext);
        for (String property: properties.keySet()) {
            muleMessage.setInvocationProperty(property, properties.get(property));
        }
        MuleEvent muleEvent;
        muleEvent = new DefaultMuleEvent(muleMessage, event);
        return chain.process(muleEvent).getMessage().getPayload();
    }

}
