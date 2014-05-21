/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.google.spreadsheet.model.Row;

public class GetCellRangeAsCsvTestCases extends GoogleSpreadsheetsTestParent {

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		try {
			testObjects = (Map<String, Object>) context.getBean("getCellRangeAsCsv");

			String spreadsheet = (String) testObjects.get("spreadsheetTitle");
			createSpreadsheet(spreadsheet);
			
			String title = (String) testObjects.get("worksheetTitle");
			int rowCount = (Integer) testObjects.get("rowCount");
			int colCount = (Integer) testObjects.get("colCount");
			
			createWorksheet(spreadsheet, title, rowCount, colCount) ;
			
			setRowValues(spreadsheet, title, (List<Row>) testObjects.get("rowsRef"));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetCellRangeAsCsv() {
		try {
			MessageProcessor flow = lookupFlowConstruct("get-cell-range-as-csv");
			testObjects.put("worksheet", (String) testObjects.get("worksheetTitle"));
			MuleEvent response = flow.process(getTestEvent(testObjects));
			
			String res = (String) response.getMessage().getPayload();
			
			assertEquals("1,1,valueOfR1C1\n1,2,valueOfR1C2\n2,1,valueOfR2C1\n2,2,valueOfR2C2", res);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@After
	public void tearDown() {
		try {
			String spreadsheet = (String) testObjects.get("spreadsheetTitle");
			deleteSpreadsheet(spreadsheet);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
