
package org.mule.module.google.spreadsheet.processors;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.processor.MessageProcessor;
import org.mule.common.DefaultResult;
import org.mule.common.Result;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.config.i18n.CoreMessages;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthManager;
import org.mule.module.google.spreadsheet.process.ProcessAdapter;
import org.mule.module.google.spreadsheet.process.ProcessCallback;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;
import org.mule.modules.google.oauth.invalidation.OAuthTokenExpiredException;


/**
 * SetCsvValuesMessageProcessor invokes the {@link org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector#setCsvValues(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String, int, int, boolean)} method in {@link GoogleSpreadSheetConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class SetCsvValuesMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor, OperationMetaDataEnabled
{

    protected Object spreadsheet;
    protected String _spreadsheetType;
    protected Object worksheet;
    protected String _worksheetType;
    protected Object csv;
    protected String _csvType;
    protected Object startingRow;
    protected int _startingRowType;
    protected Object startingColumn;
    protected int _startingColumnType;
    protected Object lineSeparator;
    protected String _lineSeparatorType;
    protected Object columnSeparator;
    protected String _columnSeparatorType;
    protected Object spreadsheetIndex;
    protected int _spreadsheetIndexType;
    protected Object worksheetIndex;
    protected int _worksheetIndexType;
    protected Object purge;
    protected boolean _purgeType;

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
    }

    public void stop()
        throws MuleException
    {
    }

    public void dispose() {
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        super.setFlowConstruct(flowConstruct);
    }

    /**
     * Sets lineSeparator
     * 
     * @param value Value to set
     */
    public void setLineSeparator(Object value) {
        this.lineSeparator = value;
    }

    /**
     * Sets worksheetIndex
     * 
     * @param value Value to set
     */
    public void setWorksheetIndex(Object value) {
        this.worksheetIndex = value;
    }

    /**
     * Sets spreadsheetIndex
     * 
     * @param value Value to set
     */
    public void setSpreadsheetIndex(Object value) {
        this.spreadsheetIndex = value;
    }

    /**
     * Sets columnSeparator
     * 
     * @param value Value to set
     */
    public void setColumnSeparator(Object value) {
        this.columnSeparator = value;
    }

    /**
     * Sets startingColumn
     * 
     * @param value Value to set
     */
    public void setStartingColumn(Object value) {
        this.startingColumn = value;
    }

    /**
     * Sets worksheet
     * 
     * @param value Value to set
     */
    public void setWorksheet(Object value) {
        this.worksheet = value;
    }

    /**
     * Sets csv
     * 
     * @param value Value to set
     */
    public void setCsv(Object value) {
        this.csv = value;
    }

    /**
     * Sets spreadsheet
     * 
     * @param value Value to set
     */
    public void setSpreadsheet(Object value) {
        this.spreadsheet = value;
    }

    /**
     * Sets purge
     * 
     * @param value Value to set
     */
    public void setPurge(Object value) {
        this.purge = value;
    }

    /**
     * Sets startingRow
     * 
     * @param value Value to set
     */
    public void setStartingRow(Object value) {
        this.startingRow = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(final MuleEvent event)
        throws MuleException
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(GoogleSpreadSheetConnectorOAuthManager.class, false, event);
            final String _transformedSpreadsheet = ((String) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_spreadsheetType").getGenericType(), null, spreadsheet));
            final String _transformedWorksheet = ((String) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_worksheetType").getGenericType(), null, worksheet));
            final String _transformedCsv = ((String) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_csvType").getGenericType(), null, csv));
            final Integer _transformedStartingRow = ((Integer) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_startingRowType").getGenericType(), null, startingRow));
            final Integer _transformedStartingColumn = ((Integer) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_startingColumnType").getGenericType(), null, startingColumn));
            final String _transformedLineSeparator = ((String) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_lineSeparatorType").getGenericType(), null, lineSeparator));
            final String _transformedColumnSeparator = ((String) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_columnSeparatorType").getGenericType(), null, columnSeparator));
            final Integer _transformedSpreadsheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_spreadsheetIndexType").getGenericType(), null, spreadsheetIndex));
            final Integer _transformedWorksheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_worksheetIndexType").getGenericType(), null, worksheetIndex));
            final Boolean _transformedPurge = ((Boolean) evaluateAndTransform(getMuleContext(), event, SetCsvValuesMessageProcessor.class.getDeclaredField("_purgeType").getGenericType(), null, purge));
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return Arrays.asList(new Class[] {OAuthTokenExpiredException.class });
                }

                public boolean isProtected() {
                    return true;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((GoogleSpreadSheetConnector) object).setCsvValues(_transformedSpreadsheet, _transformedWorksheet, _transformedCsv, _transformedStartingRow, _transformedStartingColumn, _transformedLineSeparator, _transformedColumnSeparator, _transformedSpreadsheetIndex, _transformedWorksheetIndex, _transformedPurge);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("setCsvValues"), event, e);
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(String.class)));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(void.class)));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

}
