/**
 * Mule Google Spreadsheets Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public abstract class CsvToRowsAdapter {

	private static final Logger logger = Logger.getLogger(CsvToRowsAdapter.class);
	
	public static List<Row> adapt(String input, int startingRow, int startingColumn, String columnSeparator, String lineSeparator) {
		if (StringUtils.isEmpty(input)) {
			throw new IllegalArgumentException("input is empty");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("extracting rows using lineSeparator: %s, columnSepartor:%s from payload:\n%s", lineSeparator, columnSeparator, input));
		}
		
		String[] lines = input.split(lineSeparator);
		
		List<Row> rows = new ArrayList<Row>(lines.length);
		int lineNumber = startingRow;
		
		for (String line : lines) {
			Row row = new Row();
			row.setRowNumber(lineNumber);
			
			String[] columns = line.split(columnSeparator);
			int columnNumber = startingColumn;
			
			for (String column : columns) {
				Cell cell = new Cell();
				cell.setValueOrFormula(column);
				cell.setColumnNumber(columnNumber);
				cell.setRowNumber(lineNumber);
				columnNumber++;
				row.addCell(cell);
			}
			
			rows.add(row);
			lineNumber++;
		}
		
		return rows;
	}

}
