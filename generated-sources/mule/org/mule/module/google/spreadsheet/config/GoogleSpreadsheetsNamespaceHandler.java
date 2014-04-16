
package org.mule.module.google.spreadsheet.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/google-spreadsheets</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-04-16T10:04:10-05:00", comments = "Build master.1915.dd1962d")
public class GoogleSpreadsheetsNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(GoogleSpreadsheetsNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-spreadsheets] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-spreadsheets] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config-with-oauth", new GoogleSpreadSheetConnectorConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("authorize", new AuthorizeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("authorize", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("authorize", new AuthorizeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("unauthorize", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-all-spreadsheets", new GetAllSpreadsheetsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-all-spreadsheets", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("create-spreadsheet", new CreateSpreadsheetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("create-spreadsheet", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-all-worksheets", new GetAllWorksheetsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-all-worksheets", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("create-worksheet", new CreateWorksheetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("create-worksheet", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete-worksheet", new DeleteWorksheetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete-worksheet", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("update-worksheet-metadata", new UpdateWorksheetMetadataDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("update-worksheet-metadata", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("set-row-values", new SetRowValuesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("set-row-values", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("set-csv-values", new SetCsvValuesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("set-csv-values", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-authors", new GetAuthorsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-authors", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-column-headers", new GetColumnHeadersDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-column-headers", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-spreadsheets-by-title", new GetSpreadsheetsByTitleDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-spreadsheets-by-title", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-worksheet-by-title", new GetWorksheetByTitleDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-worksheet-by-title", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("purge-worksheet", new PurgeWorksheetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("purge-worksheet", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-all-cells", new GetAllCellsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-all-cells", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-all-cells-as-csv", new GetAllCellsAsCsvDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-all-cells-as-csv", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-cell-range", new GetCellRangeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-cell-range", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-cell-range-as-csv", new GetCellRangeAsCsvDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-cell-range-as-csv", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("search", new SearchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("search", "@Processor", ex);
        }
    }

}
