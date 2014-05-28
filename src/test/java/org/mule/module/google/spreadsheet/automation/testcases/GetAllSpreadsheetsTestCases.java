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
import org.mule.module.google.spreadsheet.automation.SmokeTests;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetAllSpreadsheetsTestCases extends GoogleSpreadsheetsTestParent {

    private List<String> spreadsheetTitles;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getAllSpreadsheets");

        spreadsheetTitles = getTestRunMessageValue("spreadsheets");
        for (String spreadsheetTitle : spreadsheetTitles) {
            upsertOnTestRunMessage("spreadsheet", spreadsheetTitle);
            createSpreadsheet(spreadsheetTitle);
        }
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testGetAllSpreadsheets() {
        try {
            List<Spreadsheet> spreadsheets = runFlowAndGetPayload("get-all-spreadsheets");

            for (String spreadsheetTitle : spreadsheetTitles) {
                boolean found = false;
                for (Spreadsheet spreadsheet : spreadsheets) {
                    if (spreadsheet.getTitle().equals(spreadsheetTitle)) {
                        found = true;
                        break;
                    }
                }
                assertTrue(found);
            }

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        for (String spreadsheet : spreadsheetTitles) {
            deleteSpreadsheet(spreadsheet);
        }
    }

}
