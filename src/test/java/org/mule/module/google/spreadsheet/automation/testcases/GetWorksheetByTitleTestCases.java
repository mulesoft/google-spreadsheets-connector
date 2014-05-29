/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.automation.RegressionTests;
import org.mule.module.google.spreadsheet.model.Worksheet;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetWorksheetByTitleTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;
    List<Worksheet> createdWorksheets = new ArrayList<Worksheet>();

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getWorksheetByTitle");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);
			
			List<Map<String, Object>> worksheets = getTestRunMessageValue("worksheets");
			List<String> createdWorksheetTitles = new ArrayList<String>();

			for (Map<String, Object> worksheet : worksheets) {
				upsertOnTestRunMessage("worksheet", worksheet.get("worksheet"));
                upsertOnTestRunMessage("rowCount", worksheet.get("rowCount"));
                upsertOnTestRunMessage("colCount", worksheet.get("colCount"));
				
				Worksheet createdWorksheet = createWorksheet();
				createdWorksheets.add(createdWorksheet);
				createdWorksheetTitles.add(createdWorksheet.getTitle());
			}
			
			upsertOnTestRunMessage("createdWorksheetTitles", createdWorksheetTitles);
			upsertOnTestRunMessage("createdWorksheets", createdWorksheets);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetWorksheetByTitle_WithResults() {
		try {
			String toSearch = getTestRunMessageValue("toSearch");
			upsertOnTestRunMessage("worksheet", toSearch);

			List<Worksheet> worksheets = runFlowAndGetPayload("get-worksheet-by-title");
			
			for (Worksheet worksheet : worksheets) {
				assertTrue(worksheet.getTitle().equals(toSearch));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetWorksheetByTitle_NoResults() {
		try {
			List<String> createdWorksheetTitles = getTestRunMessageValue("createdWorksheetTitles");
			
			// Create a placeholder for a title which cannot be set for a worksheet
			// This will guarantee that no worksheet is returned with this name
			String toSearch = "SomeWorksheetTitleWhichCannotBeSet";
			// Do not run the test if a worksheet has the same as toSearch
			assertFalse(createdWorksheetTitles.contains(toSearch));

            upsertOnTestRunMessage("worksheet", toSearch);

			List<Worksheet> worksheets = runFlowAndGetPayload("get-worksheet-by-title");

			// Assert that no worksheets are returned.
			assertTrue(worksheets.isEmpty());
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}

    @After
    public void tearDown() throws Exception {
        for (Worksheet createdWorksheet : createdWorksheets) {
            upsertOnTestRunMessage("worksheet", createdWorksheet.getTitle());
            runFlowAndGetPayload("delete-worksheet");
        }
        deleteSpreadsheet(spreadsheetTitle);
    }
}