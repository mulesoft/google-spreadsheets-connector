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
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class UpdateWorksheetMetadataTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("updateWorksheetMetadata");
			
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			String worksheetTitle = (String) testObjects.get("worksheet");
			int rowCount = (Integer) testObjects.get("rowCount");
			int colCount = (Integer) testObjects.get("colCount");
			
			createSpreadsheet(spreadsheetTitle);
			Worksheet worksheet = createWorksheet(spreadsheetTitle, worksheetTitle, rowCount, colCount);
			testObjects.put("worksheetObject", worksheet);			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testUpdateWorksheetMetadata() {
		try {
			String updatedTitle = (String) testObjects.get("updatedTitle");
			int updatedColCount = (Integer) testObjects.get("updatedColCount");
			int updatedRowCount = (Integer) testObjects.get("updatedRowCount");
			
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			Worksheet worksheet = (Worksheet) testObjects.get("worksheetObject");
			
			testObjects.put("worksheet", worksheet.getTitle());
			testObjects.put("title", updatedTitle);
			testObjects.put("rowCount", updatedRowCount);
			testObjects.put("colCount", updatedColCount);
			
			MessageProcessor flow = lookupFlowConstruct("update-worksheet-metadata");
			flow.process(getTestEvent(testObjects));
			
			List<Worksheet> retrievedWorksheets = getWorksheetByTitle(spreadsheetTitle, updatedTitle);
			
			// There should only be one worksheet with this name
			Worksheet updatedWorksheet = retrievedWorksheets.get(0);
			
			assertEquals(updatedColCount, updatedWorksheet.getColCount());
			assertEquals(updatedRowCount, updatedWorksheet.getRowCount());
			assertEquals(updatedTitle, updatedWorksheet.getTitle());
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
