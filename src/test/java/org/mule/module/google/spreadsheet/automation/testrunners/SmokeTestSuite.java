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
import org.mule.module.google.spreadsheet.automation.testcases.GetAllCellsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetAllSpreadsheetsTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.GetWorksheetByTitleTestCases;
import org.mule.module.google.spreadsheet.automation.testcases.SetRowValuesTestCases;
import org.mule.module.google.spreadsheet.automation.SmokeTests;

@RunWith(Categories.class)
@IncludeCategory(SmokeTests.class)
@SuiteClasses({
	CreateSpreadsheetTestCases.class,
	CreateWorksheetTestCases.class,
	GetAllSpreadsheetsTestCases.class,
	GetWorksheetByTitleTestCases.class,
	SetRowValuesTestCases.class,
	GetAllCellsTestCases.class
})
public class SmokeTestSuite {

}
