/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import static org.junit.Assert.assertEquals;
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
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class GetAllCellsAsCsvTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getAllCellsAsCsv");
			
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			createSpreadsheet(spreadsheetTitle);

			String worksheetTitle = (String) testObjects.get("worksheet");
			int rowCount = (Integer) testObjects.get("rowCount");
			int colCount = (Integer) testObjects.get("colCount");
			
			Worksheet worksheet = createWorksheet(spreadsheetTitle, worksheetTitle, rowCount, colCount);
			testObjects.put("worksheetObject", worksheet);
			
			List<Row> rows = (List<Row>) testObjects.get("rowsRef");
			setRowValues(spreadsheetTitle, worksheet.getTitle(), rows);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Category({RegressionTests.class})
	@Test
	public void testGetAllCellsAsCsv() {
		try {
			String columnSeparator = (String) testObjects.get("columnSeparator");
			String lineSeparator = (String) testObjects.get("lineSeparator");
			
			List<Row> inputRows = (List<Row>) testObjects.get("rowsRef");
			
			MessageProcessor flow = lookupFlowConstruct("get-all-cells-as-csv");
			MuleEvent response = flow.process(getTestEvent(testObjects));

			String csvCells = (String) response.getMessage().getPayload();
			
			StringBuilder csvBuilder = new StringBuilder();
			
			for (int i = 0; i < inputRows.size(); i++) {
				Row inputRow = inputRows.get(i);
				List<Cell> cells = inputRow.getCells();
				for (int j = 0; j < cells.size(); j++) {
					csvBuilder.append(i + 1);								// Add row number (row number is not 0-based)
					csvBuilder.append(columnSeparator);						// Add the column separator
					csvBuilder.append(j + 1);								// Add the column number (column number is not 0-based)
					csvBuilder.append(columnSeparator);						// Add the column separator
					csvBuilder.append(cells.get(j).getValueOrFormula());	// Add the value of the cell
					csvBuilder.append(lineSeparator);						// Add the line separator
				}
				
			}

			// Remove the last line separator.
			csvBuilder.delete(csvBuilder.length() - lineSeparator.length(), csvBuilder.length());
			
			assertEquals(csvBuilder.toString(), csvCells);
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
