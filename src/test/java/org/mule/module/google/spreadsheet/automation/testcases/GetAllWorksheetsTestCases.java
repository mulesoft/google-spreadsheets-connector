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
import org.mule.module.google.spreadsheet.model.Worksheet;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetAllWorksheetsTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;
    private List<Worksheet> createdWorksheets = new ArrayList<Worksheet>();

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getAllWorksheets");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);


        List<Map<String, Object>> worksheets = getTestRunMessageValue("worksheets");
        for (Map<String, Object> worksheet : worksheets) {
            upsertOnTestRunMessage("worksheet", worksheet.get("worksheet"));
            upsertOnTestRunMessage("rowCount", worksheet.get("rowCount"));
            upsertOnTestRunMessage("colCount", worksheet.get("colCount"));
            Worksheet createdWorksheet = createWorksheet();
            createdWorksheets.add(createdWorksheet);
        }
    }

    @Category({RegressionTests.class})
    @Test
    public void testGetAllWorksheets() {
        try {
            List<Worksheet> worksheets = runFlowAndGetPayload("get-all-worksheets");

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
        } catch (Exception e) {
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
