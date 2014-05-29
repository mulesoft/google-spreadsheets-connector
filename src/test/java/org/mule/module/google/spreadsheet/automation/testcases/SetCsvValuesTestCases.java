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
import org.mule.module.google.spreadsheet.CsvToRowsAdapter;
import org.mule.module.google.spreadsheet.automation.RegressionTests;
import org.mule.module.google.spreadsheet.automation.SmokeTests;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SetCsvValuesTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("setCsvValues");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);

        createWorksheet();
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testSetCsvValues() {
        try {
            int startingRow = (Integer)getTestRunMessageValue("startingRow");
            int startingColumn = (Integer)getTestRunMessageValue("startingColumn");
            String columnSeparator = getTestRunMessageValue("columnSeparator");
            String lineSeparator = getTestRunMessageValue("lineSeparator");

            String csv = getTestRunMessageValue("csv");
            runFlowAndGetPayload("set-csv-values");

            List<Row> retrievedRows = runFlowAndGetPayload("get-all-cells");
            List<Row> csvRows = CsvToRowsAdapter.adapt(csv, startingRow, startingColumn, columnSeparator, lineSeparator);

            for (Row row : csvRows) {
                assertTrue(retrievedRows.contains(row));
                Row retrievedRow = retrievedRows.get(retrievedRows.indexOf(row));

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
