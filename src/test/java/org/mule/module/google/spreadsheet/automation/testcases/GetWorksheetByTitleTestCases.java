/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class GetWorksheetByTitleTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getWorksheetByTitle");

			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			createSpreadsheet(spreadsheetTitle);
			
			List<Map<String, Object>> worksheets = (List<Map<String, Object>>) testObjects.get("worksheets");
			List<String> createdWorksheetTitles = new ArrayList<String>();
			List<Worksheet> createdWorksheets = new ArrayList<Worksheet>();
			
			for (Map<String, Object> worksheet : worksheets) {
				String title = (String) worksheet.get("title");
				int rowCount = (Integer) worksheet.get("rowCount");
				int colCount = (Integer) worksheet.get("colCount");
				
				Worksheet createdWorksheet = createWorksheet(spreadsheetTitle, title, rowCount, colCount);
				createdWorksheets.add(createdWorksheet);
				createdWorksheetTitles.add(createdWorksheet.getTitle());
			}
			
			testObjects.put("createdWorksheetTitles", createdWorksheetTitles);
			testObjects.put("createdWorksheets", createdWorksheets);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetWorksheetByTitle_WithResults() {
		try {
			String toSearch = (String) testObjects.get("toSearch");
			testObjects.put("title", toSearch);

			MessageProcessor flow = lookupFlowConstruct("get-worksheet-by-title");
			MuleEvent response = flow.process(getTestEvent(testObjects));
			List<Worksheet> worksheets = (List<Worksheet>) response.getMessage().getPayload();
			
			for (Worksheet worksheet : worksheets) {
				assertTrue(worksheet.getTitle().equals(toSearch));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetWorksheetByTitle_NoResults() {
		try {
			List<String> createdWorksheetTitles = (List<String>) testObjects.get("createdWorksheetTitles");
			
			// Create a placeholder for a title which cannot be set for a worksheet
			// This will guarantee that no worksheet is returned with this name
			String toSearch = "SomeWorksheetTitleWhichCannotBeSet";
			// Do not run the test if a worksheet has the same as toSearch
			assertFalse(createdWorksheetTitles.contains(toSearch));
						
			testObjects.put("title", toSearch);

			MessageProcessor flow = lookupFlowConstruct("get-worksheet-by-title");
			MuleEvent response = flow.process(getTestEvent(testObjects));
			List<Worksheet> worksheets = (List<Worksheet>) response.getMessage().getPayload();

			// Assert that no worksheets are returned.
			assertTrue(worksheets.isEmpty());
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