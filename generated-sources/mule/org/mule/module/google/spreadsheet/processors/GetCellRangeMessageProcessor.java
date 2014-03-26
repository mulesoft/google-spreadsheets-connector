
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
import org.mule.common.metadata.DefaultListMetaDataModel;
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
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthManager;
import org.mule.module.google.spreadsheet.process.ProcessAdapter;
import org.mule.module.google.spreadsheet.process.ProcessCallback;
import org.mule.module.google.spreadsheet.process.ProcessTemplate;
import org.mule.modules.google.oauth.invalidation.OAuthTokenExpiredException;


/**
 * GetCellRangeMessageProcessor invokes the {@link org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector#getCellRange(java.lang.String, java.lang.String, int, int, int, int, int, int)} method in {@link GoogleSpreadSheetConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class GetCellRangeMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor, OperationMetaDataEnabled
{

    protected Object spreadsheet;
    protected String _spreadsheetType;
    protected Object worksheet;
    protected String _worksheetType;
    protected Object spreadsheetIndex;
    protected int _spreadsheetIndexType;
    protected Object worksheetIndex;
    protected int _worksheetIndexType;
    protected Object minRow;
    protected int _minRowType;
    protected Object maxRow;
    protected int _maxRowType;
    protected Object minCol;
    protected int _minColType;
    protected Object maxCol;
    protected int _maxColType;

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
     * Sets maxRow
     * 
     * @param value Value to set
     */
    public void setMaxRow(Object value) {
        this.maxRow = value;
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
     * Sets spreadsheet
     * 
     * @param value Value to set
     */
    public void setSpreadsheet(Object value) {
        this.spreadsheet = value;
    }

    /**
     * Sets maxCol
     * 
     * @param value Value to set
     */
    public void setMaxCol(Object value) {
        this.maxCol = value;
    }

    /**
     * Sets minCol
     * 
     * @param value Value to set
     */
    public void setMinCol(Object value) {
        this.minCol = value;
    }

    /**
     * Sets minRow
     * 
     * @param value Value to set
     */
    public void setMinRow(Object value) {
        this.minRow = value;
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
            final String _transformedSpreadsheet = ((String) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_spreadsheetType").getGenericType(), null, spreadsheet));
            final String _transformedWorksheet = ((String) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_worksheetType").getGenericType(), null, worksheet));
            final Integer _transformedSpreadsheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_spreadsheetIndexType").getGenericType(), null, spreadsheetIndex));
            final Integer _transformedWorksheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_worksheetIndexType").getGenericType(), null, worksheetIndex));
            final Integer _transformedMinRow = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_minRowType").getGenericType(), null, minRow));
            final Integer _transformedMaxRow = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_maxRowType").getGenericType(), null, maxRow));
            final Integer _transformedMinCol = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_minColType").getGenericType(), null, minCol));
            final Integer _transformedMaxCol = ((Integer) evaluateAndTransform(getMuleContext(), event, GetCellRangeMessageProcessor.class.getDeclaredField("_maxColType").getGenericType(), null, maxCol));
            Object resultPayload;
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return Arrays.asList(new Class[] {OAuthTokenExpiredException.class });
                }

                public boolean isProtected() {
                    return true;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((GoogleSpreadSheetConnector) object).getCellRange(_transformedSpreadsheet, _transformedWorksheet, _transformedSpreadsheetIndex, _transformedWorksheetIndex, _transformedMinRow, _transformedMaxRow, _transformedMinCol, _transformedMaxCol);
                }

            }
            , this, event);
            overwritePayload(event, resultPayload);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("getCellRange"), event, e);
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        return new DefaultResult<MetaData>(new DefaultMetaData(new DefaultListMetaDataModel(getPojoOrSimpleModel(Row.class))));
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
