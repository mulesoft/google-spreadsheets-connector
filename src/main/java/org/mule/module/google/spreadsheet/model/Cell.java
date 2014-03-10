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

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * This class represents a cell in a spreadsheet.
 * 
 * @author mariano.gonzalez@mulesoft.com
 */
public class Cell implements Comparable<Cell> {

	/**
	 * The number of the row it belongs to. It's a 1-based index.
	 * Defaults to zero which means that this is still unknown. 
	 */
	private int rowNumber = 0;
	
	/**
	 * The number of the represented column. It's a 1-based index.
	 * Defaults to zero which means that this is still unknown. 
	 */
	private int columnNumber = 0;
	
	/**
	 * The literal value or formula that was introduced into the cell.
	 * This is the value as the user entered it. It doesn't have any formatting
	 * or evaluation (in the case of formulas)
	 */
	private String valueOrFormula;
	
	/**
	 * The evaluated cell value. By evaluated we mean that
	 * if the user entered a formula then the result of that evaluation is returned.
	 * If a literal value was introduced but the cell was subject to some kind of formatting, then
	 * the formatted value is returned.
	 * 
	 * This is just a holder for the value, the actual formatting and evaluation happens server side
	 * at the spreadsheet's server
	 */
	private String evaluatedValue;
	
	/**
	 * Two cells are equl if the belong to the same row and column
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cell) {
			Cell o = (Cell) obj;
			return this.getColumnNumber() == o.getColumnNumber() && this.getRowNumber() == o.getRowNumber();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * column number times row number times eleven
	 */
	@Override
	public int hashCode() {
		return this.getColumnNumber() * this.getRowNumber() * 11;
	}
	
	/**
	 * A cell is considered lower than another if it's column number is lower.
	 */
	@Override
	public int compareTo(Cell o) {
		return new Integer(this.getColumnNumber()).compareTo(o.getColumnNumber());
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getValueOrFormula() {
		return valueOrFormula;
	}

	public void setValueOrFormula(String valueOrFormula) {
		this.valueOrFormula = valueOrFormula;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getEvaluatedValue() {
		return evaluatedValue;
	}

	public void setEvaluatedValue(String evaluatedValue) {
		this.evaluatedValue = evaluatedValue;
	}
	
}
