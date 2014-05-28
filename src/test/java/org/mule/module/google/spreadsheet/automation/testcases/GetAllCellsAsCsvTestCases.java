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
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetAllCellsAsCsvTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getAllCellsAsCsv");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);

        createWorksheet();

        setRowValues();
    }

    @Test
    @Category({RegressionTests.class})
    public void testGetAllCellsAsCsv() {
        try {
            String columnSeparator = getTestRunMessageValue("columnSeparator");
            String lineSeparator = getTestRunMessageValue("lineSeparator");

            List<Row> inputRows = getTestRunMessageValue("rowsRef");

            String csvCells = runFlowAndGetPayload("get-all-cells-as-csv");

            StringBuilder csvBuilder = new StringBuilder();

            for (int i = 0; i < inputRows.size(); i++) {
                Row inputRow = inputRows.get(i);
                List<Cell> cells = inputRow.getCells();
                for (int j = 0; j < cells.size(); j++) {
                    csvBuilder.append(i + 1);                                // Add row number (row number is not 0-based)
                    csvBuilder.append(columnSeparator);                        // Add the column separator
                    csvBuilder.append(j + 1);                                // Add the column number (column number is not 0-based)
                    csvBuilder.append(columnSeparator);                        // Add the column separator
                    csvBuilder.append(cells.get(j).getValueOrFormula());    // Add the value of the cell
                    csvBuilder.append(lineSeparator);                        // Add the line separator
                }

            }

            // Remove the last line separator.
            csvBuilder.delete(csvBuilder.length() - lineSeparator.length(), csvBuilder.length());

            assertEquals(csvBuilder.toString(), csvCells);
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
