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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.module.google.spreadsheet.automation.RegressionTests;
import org.mule.module.google.spreadsheet.automation.SmokeTests;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@Ignore
public class CreateSpreadsheetTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("createSpreadsheet");
        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
    }

    @Test
    @Category({SmokeTests.class, RegressionTests.class})
    public void testCreateSpreadsheet() {
        try {

            // The Spreadsheets API does not currently provide a way to delete a spreadsheet.
            // Please manually delete to create the spreadsheet using the test cases.
            if (isSpreadsheetAvailable(spreadsheetTitle))
                return;

            runFlowAndGetPayload("create-spreadsheet");

            assertTrue(isSpreadsheetAvailable(spreadsheetTitle));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        deleteSpreadsheet(spreadsheetTitle);
    }

}
