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

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.model.Spreadsheet;

public class GetSpreadsheetsByTitleTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getSpreadsheetsByTitle");

			List<String> spreadsheetTitles = (List<String>) testObjects.get("spreadsheets");
			for (String spreadsheetTitle : spreadsheetTitles) {
				createSpreadsheet(spreadsheetTitle);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetSpreadsheetsByTitle_WithResults() {
		try {
			String toSearch = (String) testObjects.get("toSearch");
						
			testObjects.put("title", toSearch);

			MessageProcessor flow = lookupFlowConstruct("get-spreadsheets-by-title");
			MuleEvent response = flow.process(getTestEvent(testObjects));
			List<Spreadsheet> spreadsheets = (List<Spreadsheet>) response.getMessage().getPayload();
			
			for (Spreadsheet spreadsheet : spreadsheets) {
				assertTrue(spreadsheet.getTitle().equals(toSearch));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetSpreadsheetsByTitle_NoResults() {
		try {
			List<String> spreadsheetNames = (List<String>) testObjects.get("spreadsheets");
			
			// Create a placeholder for a title which cannot be set for a spreadsheet
			// This will guarantee that no spreadsheet is returned with this name
			String toSearch = "SomeSpreadsheetTitleWhichCannotBeSet";
			// Do not run the test if some spreadsheet has the same as toSearch
			assertFalse(spreadsheetNames.contains(toSearch));
						
			testObjects.put("title", toSearch);

			MessageProcessor flow = lookupFlowConstruct("get-spreadsheets-by-title");
			MuleEvent response = flow.process(getTestEvent(testObjects));
			List<Spreadsheet> spreadsheets = (List<Spreadsheet>) response.getMessage().getPayload();

			// Assert that no spreadsheets are returned.
			assertTrue(spreadsheets.isEmpty());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@After
	public void tearDown() {
		try {
			List<String> spreadsheets = (List<String>) testObjects.get("spreadsheets");
			for (String spreadsheet : spreadsheets) {
				deleteSpreadsheet(spreadsheet);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
