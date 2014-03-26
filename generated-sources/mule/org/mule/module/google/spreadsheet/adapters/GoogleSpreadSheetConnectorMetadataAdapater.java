
package org.mule.module.google.spreadsheet.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;


/**
 * A <code>GoogleSpreadSheetConnectorMetadataAdapater</code> is a wrapper around {@link GoogleSpreadSheetConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-03-26T12:37:34-05:00", comments = "Build M4.1875.17b58a3")
public class GoogleSpreadSheetConnectorMetadataAdapater
    extends GoogleSpreadSheetConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Google Spreadsheets";
    private final static String MODULE_VERSION = "2.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.5.0-M4";
    private final static String DEVKIT_BUILD = "M4.1875.17b58a3";
    private final static String MIN_MULE_VERSION = "3.5";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}
