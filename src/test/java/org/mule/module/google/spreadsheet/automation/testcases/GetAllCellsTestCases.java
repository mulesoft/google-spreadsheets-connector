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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class GetAllCellsTestCases extends GoogleSpreadsheetsTestParent {
	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getAllCells");
			
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
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testGetAllCells() {
		try {
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			Worksheet worksheet = (Worksheet) testObjects.get("worksheetObject");
			List<Row> inputRows = (List<Row>) testObjects.get("rowsRef");
			
			List<Row> allRows = getAllCells(spreadsheetTitle, worksheet.getTitle());
			
			assertTrue(allRows.size() == inputRows.size());
			
			for (Row row : inputRows) {
				assertTrue(allRows.contains(row));
				Row retrievedRow = allRows.get(allRows.indexOf(row));

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
