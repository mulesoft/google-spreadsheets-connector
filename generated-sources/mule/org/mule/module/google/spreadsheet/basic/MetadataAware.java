
package org.mule.module.google.spreadsheet.basic;

import javax.annotation.Generated;


/**
 *  This interface is implemented for every {@link org.mule.api.annotations.Module} and {@link org.mule.api.annotations.Connector} annotated class and its purpose is to define a contract to query the annotated class about its metadata.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface MetadataAware {

     /**
     * Returns the user-friendly name of this module
     */
    String getModuleName();

    /**
     * Returns the version of this module
     */
    String getModuleVersion();

    /**
     * Returns the version of the DevKit used to create this module
     */
    String getDevkitVersion();

    /**
     * Returns the build of the DevKit used to create this module
     */
    String getDevkitBuild();
}
