
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
 * UpdateWorksheetMetadataMessageProcessor invokes the {@link org.mule.module.google.spreadsheet.GoogleSpreadSheetConnector#updateWorksheetMetadata(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, int, int, int, int)} method in {@link GoogleSpreadSheetConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class UpdateWorksheetMetadataMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor, OperationMetaDataEnabled
{

    protected Object spreadsheet;
    protected String _spreadsheetType;
    protected Object worksheet;
    protected String _worksheetType;
    protected Object title;
    protected String _titleType;
    protected Object draft;
    protected Boolean _draftType;
    protected Object canEdit;
    protected Boolean _canEditType;
    protected Object summary;
    protected String _summaryType;
    protected Object rowCount;
    protected int _rowCountType;
    protected Object colCount;
    protected int _colCountType;
    protected Object spreadsheetIndex;
    protected int _spreadsheetIndexType;
    protected Object worksheetIndex;
    protected int _worksheetIndexType;

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
     * Sets summary
     * 
     * @param value Value to set
     */
    public void setSummary(Object value) {
        this.summary = value;
    }

    /**
     * Sets draft
     * 
     * @param value Value to set
     */
    public void setDraft(Object value) {
        this.draft = value;
    }

    /**
     * Sets title
     * 
     * @param value Value to set
     */
    public void setTitle(Object value) {
        this.title = value;
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
     * Sets worksheet
     * 
     * @param value Value to set
     */
    public void setWorksheet(Object value) {
        this.worksheet = value;
    }

    /**
     * Sets rowCount
     * 
     * @param value Value to set
     */
    public void setRowCount(Object value) {
        this.rowCount = value;
    }

    /**
     * Sets colCount
     * 
     * @param value Value to set
     */
    public void setColCount(Object value) {
        this.colCount = value;
    }

    /**
     * Sets canEdit
     * 
     * @param value Value to set
     */
    public void setCanEdit(Object value) {
        this.canEdit = value;
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
            final String _transformedSpreadsheet = ((String) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_spreadsheetType").getGenericType(), null, spreadsheet));
            final String _transformedWorksheet = ((String) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_worksheetType").getGenericType(), null, worksheet));
            final String _transformedTitle = ((String) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_titleType").getGenericType(), null, title));
            final Boolean _transformedDraft = ((Boolean) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_draftType").getGenericType(), null, draft));
            final Boolean _transformedCanEdit = ((Boolean) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_canEditType").getGenericType(), null, canEdit));
            final String _transformedSummary = ((String) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_summaryType").getGenericType(), null, summary));
            final Integer _transformedRowCount = ((Integer) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_rowCountType").getGenericType(), null, rowCount));
            final Integer _transformedColCount = ((Integer) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_colCountType").getGenericType(), null, colCount));
            final Integer _transformedSpreadsheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_spreadsheetIndexType").getGenericType(), null, spreadsheetIndex));
            final Integer _transformedWorksheetIndex = ((Integer) evaluateAndTransform(getMuleContext(), event, UpdateWorksheetMetadataMessageProcessor.class.getDeclaredField("_worksheetIndexType").getGenericType(), null, worksheetIndex));
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
                    ((GoogleSpreadSheetConnector) object).updateWorksheetMetadata(_transformedSpreadsheet, _transformedWorksheet, _transformedTitle, _transformedDraft, _transformedCanEdit, _transformedSummary, _transformedRowCount, _transformedColCount, _transformedSpreadsheetIndex, _transformedWorksheetIndex);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("updateWorksheetMetadata"), event, e);
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
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
