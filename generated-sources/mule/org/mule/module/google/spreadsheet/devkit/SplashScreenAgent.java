
package org.mule.module.google.spreadsheet.devkit;

import javax.annotation.Generated;


/**
 * Marks DevKit {@link org.mule.api.agent.Agent} implementations to take care of logging information at Mule app level mainly for troubleshooting purposes.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface SplashScreenAgent {

      /**
     * Print version information for all the modules used by the application
     */
    void splash();

    /**
     * Retrieve the count of modules used by the application
     *
     * @return
     */
    int getExtensionsCount();
}
