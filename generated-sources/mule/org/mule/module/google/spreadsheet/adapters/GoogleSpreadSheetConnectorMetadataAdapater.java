
package org.mule.module.google.spreadsheet.adapters;

import javax.annotation.Generated;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.basic.MetadataAware;


/**
 * A <code>GoogleSpreadSheetConnectorMetadataAdapater</code> is a wrapper around {@link GoogleSpreadSheetConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class GoogleSpreadSheetConnectorMetadataAdapater
    extends GoogleSpreadSheetConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Google Spreadsheets";
    private final static String MODULE_VERSION = "1.5.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.4.3";
    private final static String DEVKIT_BUILD = "3.4.3.1620.30ea288";

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

}
