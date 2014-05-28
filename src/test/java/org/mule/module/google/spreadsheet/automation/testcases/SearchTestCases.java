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
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SearchTestCases extends GoogleSpreadsheetsTestParent {

    private String spreadsheetTitle;

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("search");

        spreadsheetTitle = getTestRunMessageValue("spreadsheet");
        createSpreadsheet(spreadsheetTitle);

        createWorksheet();

        setRowValues();
    }

    @Category({RegressionTests.class})
	@Test
	public void testSearch() {
		try {
            List<Row> res = runFlowAndGetPayload("search");

            assertEquals(1, res.size());
		}
		catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
	}

    @After
    public void tearDown() throws Exception {
        runFlowAndGetPayload("delete-worksheet");
        deleteSpreadsheet(spreadsheetTitle);
    }
}
