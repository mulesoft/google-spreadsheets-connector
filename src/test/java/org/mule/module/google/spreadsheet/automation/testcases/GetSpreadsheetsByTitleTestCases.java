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
import org.mule.module.google.spreadsheet.automation.RegressionTests;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class GetSpreadsheetsByTitleTestCases extends GoogleSpreadsheetsTestParent {

    private List<String> spreadsheetTitles;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getSpreadsheetsByTitle");

        spreadsheetTitles = getTestRunMessageValue("spreadsheets");
        for (String spreadsheetTitle : spreadsheetTitles) {
            upsertOnTestRunMessage("spreadsheet", spreadsheetTitle);
            createSpreadsheet(spreadsheetTitle);
        }
    }
	
	@Category({RegressionTests.class})
	@Test
	public void testGetSpreadsheetsByTitle_WithResults() {
		try {
            String toSearch = getTestRunMessageValue("toSearch");
            upsertOnTestRunMessage("spreadsheet", toSearch);

            List<Spreadsheet> spreadsheets = runFlowAndGetPayload("get-spreadsheets-by-title");

            for (Spreadsheet spreadsheet : spreadsheets) {
				assertTrue(spreadsheet.getTitle().equals(toSearch));
			}
		}
		catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetSpreadsheetsByTitle_NoResults() {
		try {
            List<String> spreadsheetNames = getTestRunMessageValue("spreadsheets");

            // Create a placeholder for a title which cannot be set for a spreadsheet
			// This will guarantee that no spreadsheet is returned with this name
			String toSearch = "SomeSpreadsheetTitleWhichCannotBeSet";
			// Do not run the test if some spreadsheet has the same as toSearch
			assertFalse(spreadsheetNames.contains(toSearch));

            upsertOnTestRunMessage("spreadsheet", toSearch);

            List<Spreadsheet> spreadsheets = runFlowAndGetPayload("get-spreadsheets-by-title");

            // Assert that no spreadsheets are returned.
			assertTrue(spreadsheets.isEmpty());
		}
		catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
	}

    @After
    public void tearDown() throws Exception {
        for (String createdSpreadsheet : spreadsheetTitles) {
            deleteSpreadsheet(createdSpreadsheet);
        }
    }
}
