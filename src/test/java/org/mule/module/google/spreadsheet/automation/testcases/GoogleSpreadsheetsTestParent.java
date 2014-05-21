/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.api.MuleEvent;
import org.mule.api.config.MuleProperties;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.store.ObjectStore;
import org.mule.api.store.ObjectStoreException;
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.module.google.spreadsheet.model.Worksheet;
import org.mule.module.google.spreadsheet.oauth.GoogleSpreadSheetConnectorOAuthState;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GoogleSpreadsheetsTestParent extends FunctionalTestCase {
	
	// Set global timeout of tests to 10minutes
    @Rule
    public Timeout globalTimeout = new Timeout(600000);
    
	protected static final String[] SPRING_CONFIG_FILES = new String[] { "AutomationSpringBeans.xml" };
	protected static ApplicationContext context;
	protected Map<String, Object> testObjects;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Before
	public void init() throws ObjectStoreException {
		ObjectStore objectStore = muleContext.getRegistry().lookupObject(MuleProperties.DEFAULT_USER_OBJECT_STORE_NAME);
		objectStore.store("accessTokenId", (GoogleSpreadSheetConnectorOAuthState)context.getBean("connectorOAuthState"));
	}
	
	@Override
	protected String getConfigResources() {
		return "automation-test-flows.xml";
	}

	protected MessageProcessor lookupFlowConstruct(String name) {
		return (MessageProcessor) muleContext.getRegistry()
				.lookupFlowConstruct(name);
	}

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILES);
	}
	
	public void createSpreadsheet(String title) throws Exception {
		testObjects.put("title", title);
		MessageProcessor flow = lookupFlowConstruct("create-spreadsheet");
		flow.process(getTestEvent(testObjects));
	}

	@SuppressWarnings("unchecked")
	public List<Spreadsheet> getAllSpreadsheets() throws Exception {
		MessageProcessor flow = lookupFlowConstruct("get-all-spreadsheets");
		MuleEvent response = flow.process(getTestEvent(testObjects));
		return (List<Spreadsheet>) response.getMessage().getPayload();
	}
	
	public Worksheet createWorksheet(String spreadsheet, String title, int rowCount, int colCount) throws Exception {
		testObjects.put("spreadsheet", spreadsheet);
		testObjects.put("title", title);
		testObjects.put("rowCount", rowCount);
		testObjects.put("colCount", colCount);
		
		MessageProcessor flow = lookupFlowConstruct("create-worksheet");
		MuleEvent response = flow.process(getTestEvent(testObjects));
		return (Worksheet) response.getMessage().getPayload();
	}
	
	public void deleteWorksheet(String spreadsheet, String worksheet) throws Exception {
		testObjects.put("spreadsheet", spreadsheet);
		testObjects.put("worksheet", worksheet);
		
		MessageProcessor flow = lookupFlowConstruct("delete-worksheet");
		MuleEvent response = flow.process(getTestEvent(testObjects));
	}
	
	@SuppressWarnings("unchecked")
	public List<Worksheet> getWorksheetByTitle(String spreadsheet, String title) throws Exception {
		testObjects.put("spreadsheet", spreadsheet);
		testObjects.put("title", title);
		
		MessageProcessor flow = lookupFlowConstruct("get-worksheet-by-title");
		MuleEvent response = flow.process(getTestEvent(testObjects));
		return (List<Worksheet>) response.getMessage().getPayload();
	}
	
	public void deleteSpreadsheet(String spreadsheet) throws Exception {
		// Dummy method. Will be implemented when functionality is provided
	}
	
	public void setRowValues(String spreadsheet, String worksheet, List<Row> rows) throws Exception {
		setRowValues(spreadsheet, worksheet, rows, true);
	}

	public void setRowValues(String spreadsheet, String worksheet, List<Row> rows, boolean purge) throws Exception {
		testObjects.put("spreadsheet", spreadsheet);
		testObjects.put("worksheet", worksheet);
		testObjects.put("rowsRef", rows);
		testObjects.put("purge", purge);
		
		MessageProcessor flow = lookupFlowConstruct("set-row-values");
		flow.process(getTestEvent(testObjects));
	}
	
	@SuppressWarnings("unchecked")
	public List<Row> getAllCells(String spreadsheet, String worksheet) throws Exception {
		testObjects.put("spreadsheet", spreadsheet);
		testObjects.put("worksheet", worksheet);
		
		MessageProcessor flow = lookupFlowConstruct("get-all-cells");
		MuleEvent response = flow.process(getTestEvent(testObjects));
		return (List<Row>) response.getMessage().getPayload();
	}
	
	@SuppressWarnings("unchecked")
	protected boolean isRowEqual(Row row1, Row row2) {
		List<Cell> inputCells = row1.getCells();
		List<Cell> retrievedCells = row2.getCells();

		for (final Cell cell : inputCells) {
			List<Cell> matchingCells = (List<Cell>) CollectionUtils.select(retrievedCells, new Predicate() {
				
				@Override
				public boolean evaluate(Object object) {
					Cell cellObject = (Cell) object;
					return (cell.getColumnNumber() == cellObject.getColumnNumber())
							&& (cell.getRowNumber() == cellObject.getRowNumber())
							&& (StringUtils.equals(cell.getValueOrFormula(), cellObject.getValueOrFormula()));
				}
			});
			
			if (matchingCells.size() != 1)
				return false;
		}
		return true;
	}
	
}
