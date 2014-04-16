
package org.mule.module.google.spreadsheet.processors;

import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthManager;
import org.mule.modules.google.AccessType;
import org.mule.modules.google.ForcePrompt;
import org.mule.security.oauth.processor.BaseOAuth2AuthorizeMessageProcessor;

@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:04:10-05:00", comments = "Build master.1915.dd1962d")
public class AuthorizeMessageProcessor
    extends BaseOAuth2AuthorizeMessageProcessor<GoogleSpreadSheetConnectorOAuthManager>
{

    private final static Pattern AUTH_CODE_PATTERN = Pattern.compile("code=([^&]+)");
    private Object access_type;
    private AccessType _access_typeType;
    private Object force_prompt;
    private ForcePrompt _force_promptType;

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

    @Override
    protected String getAuthCodeRegex() {
        return AUTH_CODE_PATTERN.pattern();
    }

    @Override
    protected Class<GoogleSpreadSheetConnectorOAuthManager> getOAuthManagerClass() {
        return GoogleSpreadSheetConnectorOAuthManager.class;
    }

}
