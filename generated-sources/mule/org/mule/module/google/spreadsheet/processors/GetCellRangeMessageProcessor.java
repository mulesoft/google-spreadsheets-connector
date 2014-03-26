
package org.mule.module.google.spreadsheet.processors;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultListMetaDataModel;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthManager;
import org.mule.modules.google.oauth.invalidation.OAuthTokenExpiredException;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * GetCellRangeMessageProcessor invokes the {@link org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector#getCellRange(java.lang.String, java.lang.String, int, int, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)} method in {@link GoogleSpreadSheetConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-03-26T12:37:34-05:00", comments = "Build M4.1875.17b58a3")
public class GetCellRangeMessageProcessor
    extends AbstractConnectedProcessor
    implements MessageProcessor, OperationMetaDataEnabled
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
    protected Integer _minRowType;
    protected Object maxRow;
    protected Integer _maxRowType;
    protected Object minCol;
    protected Integer _minColType;
    protected Object maxCol;
    protected Integer _maxColType;

    public GetCellRangeMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
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
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
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


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return Arrays.asList(((Class<? extends Exception> []) new Class[] {OAuthTokenExpiredException.class }));
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
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
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

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(GoogleSpreadSheetConnector.class, true, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at GoogleSpreadSheetConnector at getCellRange retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
