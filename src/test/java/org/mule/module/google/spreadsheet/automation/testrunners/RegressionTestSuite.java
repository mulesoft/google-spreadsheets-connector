/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testrunners;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.module.google.spreadsheet.automation.testcases.CreateSpreadsheetTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.CreateWorksheetTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAllCellsAsCsvTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAllCellsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAllSpreadsheetsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAllWorksheetsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAuthorsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetCellRangeAsCsvTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetCellRangeTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetColumnHeadersTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetSpreadsheetsByTitleTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetWorksheetByTitleTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.PurgeWorksheetTestCases;
import org.mule.module.google.spreadsheet.automation.RegressionTests;
import org.mule.module.google.spreadsheet.automation.testcases.SearchTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.SetCsvValuesTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.SetRowValuesTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.UpdateWorksheetMetadataTestCases;

@RunWith(Categories.class)
@IncludeCategory(RegressionTests.class)
@SuiteClasses({
	CreateSpreadsheetTestCases.class,
	CreateWorksheetTestCases.class,
	GetAllCellsAsCsvTestCases.class,
	GetAllCellsTestCases.class,
	GetAllSpreadsheetsTestCases.class,
	GetAllWorksheetsTestCases.class,
	GetAuthorsTestCases.class,
	GetCellRangeAsCsvTestCases.class,
	GetCellRangeTestCases.class,
	GetColumnHeadersTestCases.class,
	GetSpreadsheetsByTitleTestCases.class,
	GetWorksheetByTitleTestCases.class,
	PurgeWorksheetTestCases.class,
	SearchTestCases.class,
	SetCsvValuesTestCases.class,
	SetRowValuesTestCases.class,
	UpdateWorksheetMetadataTestCases.class
})
public class RegressionTestSuite {

}
