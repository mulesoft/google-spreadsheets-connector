
package org.mule.module.google.spreadsheet.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/google-spreadsheets</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class GoogleSpreadsheetsNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config-with-oauth", new GoogleSpreadSheetConnectorConfigDefinitionParser());
        registerBeanDefinitionParser("authorize", new AuthorizeDefinitionParser());
        registerBeanDefinitionParser("unauthorize", new UnauthorizeDefinitionParser());
        registerBeanDefinitionParser("get-all-spreadsheets", new GetAllSpreadsheetsDefinitionParser());
        registerBeanDefinitionParser("create-spreadsheet", new CreateSpreadsheetDefinitionParser());
        registerBeanDefinitionParser("get-all-worksheets", new GetAllWorksheetsDefinitionParser());
        registerBeanDefinitionParser("create-worksheet", new CreateWorksheetDefinitionParser());
        registerBeanDefinitionParser("delete-worksheet", new DeleteWorksheetDefinitionParser());
        registerBeanDefinitionParser("update-worksheet-metadata", new UpdateWorksheetMetadataDefinitionParser());
        registerBeanDefinitionParser("set-row-values", new SetRowValuesDefinitionParser());
        registerBeanDefinitionParser("set-csv-values", new SetCsvValuesDefinitionParser());
        registerBeanDefinitionParser("get-authors", new GetAuthorsDefinitionParser());
        registerBeanDefinitionParser("get-column-headers", new GetColumnHeadersDefinitionParser());
        registerBeanDefinitionParser("get-spreadsheets-by-title", new GetSpreadsheetsByTitleDefinitionParser());
        registerBeanDefinitionParser("get-worksheet-by-title", new GetWorksheetByTitleDefinitionParser());
        registerBeanDefinitionParser("purge-worksheet", new PurgeWorksheetDefinitionParser());
        registerBeanDefinitionParser("get-all-cells", new GetAllCellsDefinitionParser());
        registerBeanDefinitionParser("get-all-cells-as-csv", new GetAllCellsAsCsvDefinitionParser());
        registerBeanDefinitionParser("get-cell-range", new GetCellRangeDefinitionParser());
        registerBeanDefinitionParser("get-cell-range-as-csv", new GetCellRangeAsCsvDefinitionParser());
        registerBeanDefinitionParser("search", new SearchDefinitionParser());
    }

}
