/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.CsvToRowsAdapter;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class SetCsvValuesTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("setCsvValues");
			
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			createSpreadsheet(spreadsheetTitle);

			String worksheetTitle = (String) testObjects.get("worksheet");
			int rowCount = (Integer) testObjects.get("rowCount");
			int colCount = (Integer) testObjects.get("colCount");
			
			Worksheet worksheet = createWorksheet(spreadsheetTitle, worksheetTitle, rowCount, colCount);
			testObjects.put("worksheetObject", worksheet);			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testSetCsvValues() {
		try {
			int startingRow = (Integer) testObjects.get("startingRow");
			int startingColumn = (Integer) testObjects.get("startingColumn");
			String columnSeparator = (String) testObjects.get("columnSeparator");
			String lineSeparator = (String) testObjects.get("lineSeparator");
			
			String csv = (String) testObjects.get("csv");
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			Worksheet worksheet = (Worksheet) testObjects.get("worksheetObject");
			
			testObjects.put("worksheet", worksheet.getTitle());

			MessageProcessor flow = lookupFlowConstruct("set-csv-values");
			MuleEvent response = flow.process(getTestEvent(testObjects));

			List<Row> retrievedRows = getAllCells(spreadsheetTitle, worksheet.getTitle());
			List<Row> csvRows = CsvToRowsAdapter.adapt(csv, startingRow, startingColumn, columnSeparator, lineSeparator);
			
			for (Row row : csvRows) {
				assertTrue(retrievedRows.contains(row));
				Row retrievedRow = retrievedRows.get(retrievedRows.indexOf(row));
				
				boolean equals = isRowEqual(row, retrievedRow);
				assertTrue(equals);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@After
	public void tearDown() {
		try {
			String spreadsheet = (String) testObjects.get("spreadsheet");
			deleteSpreadsheet(spreadsheet);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
