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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.model.Worksheet;

public class GetAllWorksheetsTestCases extends GoogleSpreadsheetsTestParent {

	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getAllWorksheets");
			
			String spreadsheetTitle = (String) testObjects.get("spreadsheet");
			createSpreadsheet(spreadsheetTitle);
			
			List<Worksheet> createdWorksheets = new ArrayList<Worksheet>();
			List<Map<String, Object>> worksheets = (List<Map<String, Object>>) testObjects.get("worksheets");
			for (Map<String, Object> worksheet : worksheets) {
				String title = (String) worksheet.get("title");
				int colCount = (Integer) worksheet.get("colCount");
				int rowCount = (Integer) worksheet.get("rowCount");
				
				Worksheet createdWorksheet = createWorksheet(spreadsheetTitle, title, rowCount, colCount);
				createdWorksheets.add(createdWorksheet);
			}
			
			testObjects.put("createdWorksheets", createdWorksheets);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetAllWorksheets() {
		try {
			List<Worksheet> createdWorksheets = (List<Worksheet>) testObjects.get("createdWorksheets");
			
			MessageProcessor flow = lookupFlowConstruct("get-all-worksheets");
			MuleEvent response = flow.process(getTestEvent(testObjects));
			
			List<Worksheet> worksheets = (List<Worksheet>) response.getMessage().getPayload();
			
			// Assert that the worksheet size is always 1 above the number of created worksheets 
			// (Sheet1 is created by default)
			assertTrue(worksheets.size() == createdWorksheets.size() + 1);
			
			// .contains() method does not work for List<Worksheet>, 
			// so loop over them manually and perform assertions
			for (Worksheet createdWorksheet : createdWorksheets) {
				boolean found = false;
				for (Worksheet worksheet : worksheets) {
					if (worksheet.getId().equals(createdWorksheet.getId())) {
						found = true;
						break;
					}
				}
				assertTrue(found);
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
