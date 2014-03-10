/**
 * Mule Google Spreadsheets Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.model;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class RowBuilder {

	private Row row = new Row();
	
	public RowBuilder setNumber(int number) {
		row.setRowNumber(number);
		return this;
	}
	
	public RowBuilder addCell(int columnNumber, String valueOrFormula) {
		Cell cell = new Cell();
		cell.setColumnNumber(columnNumber);
		cell.setValueOrFormula(valueOrFormula);
		row.addCell(cell);
		return this;
	}
	
	public Row build() {
		Row retVal = row;
		this.row = new Row();
		
		return retVal;
	}
}
