
package org.mule.module.google.spreadsheet.process;

import javax.annotation.Generated;


/**
 * Callback with logic to execute within a controlled environment provided by {@link ProcessTemplate}
 *  @param <T> type of the return value of the processing execution
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface ProcessCallback<T,O >{

    T process(O object) throws Exception;

    java.util.List<Class> getManagedExceptions();

    boolean isProtected();

}
