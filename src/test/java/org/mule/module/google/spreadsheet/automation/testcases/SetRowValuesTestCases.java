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
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SetRowValuesTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("setRowValues");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);

        createWorksheet();
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testSetRowValues() {
        try {
            List<Row> inputRows = getTestRunMessageValue("rowsRef");


            runFlowAndGetPayload("set-row-values");

            List<Row> allRows = runFlowAndGetPayload("get-all-cells");

            assertTrue(allRows.size() == inputRows.size());

            for (Row row : inputRows) {
                assertTrue(allRows.contains(row));
                Row retrievedRow = allRows.get(allRows.indexOf(row));

                boolean equals = isRowEqual(row, retrievedRow);
                assertTrue(equals);
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        runFlowAndGetPayload("delete-worksheet");
        deleteSpreadsheet(spreadsheetTitle);
    }
}
