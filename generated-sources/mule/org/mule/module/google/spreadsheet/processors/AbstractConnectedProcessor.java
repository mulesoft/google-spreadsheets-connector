
package org.mule.module.google.spreadsheet.processors;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public abstract class AbstractConnectedProcessor
    extends AbstractExpressionEvaluator
{

    private Object accessTokenId;
    private String _accessTokenIdType;

    /**
     * Retrieves accessTokenId
     * 
     */
    public Object getAccessTokenId() {
        return this.accessTokenId;
    }

    /**
     * Sets accessTokenId
     * 
     * @param value Value to set
     */
    public void setAccessTokenId(Object value) {
        this.accessTokenId = value;
    }

}
