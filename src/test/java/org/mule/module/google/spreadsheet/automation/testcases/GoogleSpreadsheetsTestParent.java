/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.automation.testcases;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.api.config.MuleProperties;
import org.mule.api.store.ObjectStore;
import org.mule.api.store.ObjectStoreException;
import org.mule.common.security.oauth.OAuthState;
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.module.google.spreadsheet.model.Worksheet;
import org.mule.modules.tests.ConnectorTestCase;

import java.util.List;

public class GoogleSpreadsheetsTestParent extends ConnectorTestCase {

    // Set global timeout of tests to 10minutes
    @Rule
    public Timeout globalTimeout = new Timeout(600000);

    @Before
    public void init() throws ObjectStoreException {
        ObjectStore objectStore = muleContext.getRegistry().lookupObject(MuleProperties.DEFAULT_USER_OBJECT_STORE_NAME);
        objectStore.store("accessTokenId", (OAuthState) getBeanFromContext("connectorOAuthState"));
    }

    protected boolean isSpreadsheetAvailable(String title) throws Exception {
        List<Spreadsheet> spreadsheets = getAllSpreadsheets();

        boolean found = false;
        for (Spreadsheet spreadsheet : spreadsheets) {
            if (spreadsheet.getTitle().equals(title)) {
                found = true;
            }
        }
        return found;
    }

    protected void createSpreadsheet(String title) throws Exception {
        if (isSpreadsheetAvailable(title))
            return;
        runFlowAndGetPayload("create-spreadsheet");
    }


    public List<Spreadsheet> getAllSpreadsheets() throws Exception {
        // Wait for the previous message-processor to complete processing.
        Thread.sleep(5000);
        return runFlowAndGetPayload("get-all-spreadsheets");
    }

    public Worksheet createWorksheet() throws Exception {
        return runFlowAndGetPayload("create-worksheet");
    }

    public void deleteWorksheet(String spreadsheet, String worksheet) throws Exception {
        upsertOnTestRunMessage("spreadsheet", spreadsheet);
        upsertOnTestRunMessage("worksheet", worksheet);

        runFlowAndGetPayload("delete-worksheet");
    }

    public List<Worksheet> getWorksheetByTitle(String spreadsheet, String title) throws Exception {
        upsertOnTestRunMessage("spreadsheet", spreadsheet);
        upsertOnTestRunMessage("worksheet", title);

        return runFlowAndGetPayload("get-worksheet-by-title");
    }

    public void deleteSpreadsheet(String spreadsheet) throws Exception {
        // Dummy method. Will be implemented when functionality is provided
    }

    public void setRowValues() throws Exception {
        runFlowAndGetPayload("set-row-values");
    }

    protected boolean isRowEqual(Row row1, Row row2) {
        List<Cell> inputCells = row1.getCells();
        List<Cell> retrievedCells = row2.getCells();

		for (final Cell cell : inputCells) {
			List<Cell> matchingCells = (List<Cell>) CollectionUtils.select(retrievedCells, new Predicate() {

                @Override
                public boolean evaluate(Object object) {
                    Cell cellObject = (Cell) object;
                    return (cell.getColumnNumber() == cellObject.getColumnNumber())
                            && (cell.getRowNumber() == cellObject.getRowNumber())
                            && (StringUtils.equals(cell.getValueOrFormula(), cellObject.getValueOrFormula()));
                }
            });

            if (matchingCells.size() != 1)
                return false;
        }
		return true;
	}

}
