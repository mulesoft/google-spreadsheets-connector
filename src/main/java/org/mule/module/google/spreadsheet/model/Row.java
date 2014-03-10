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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class Row implements Comparable<Row>{

	/**
	 * Defaults to zero
	 */
	private int rowNumber = 0;
	
	/**
	 * All the initialized cells in the row
	 */
	private List<Cell> cells = new ArrayList<Cell>();
	
	public Row(){}
	
	@Override
	public int compareTo(Row o) {
		return new Integer(this.rowNumber).compareTo(o.getRowNumber());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Row) {
			Row o = (Row) obj;
			return this.rowNumber == o.rowNumber;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		return this.rowNumber;
	}
	
	public void sortCells() {
		Collections.sort(this.cells);
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
		for (Cell cell : cells) {
			cell.setRowNumber(this.rowNumber);
		}
	}
	
	/**
	 * Adds the cell. The cells row number is modified to match
	 * this row's row number
	 * @param cell - the cell
	 */
	public void addCell(Cell cell){
		cell.setRowNumber(this.rowNumber);
		this.cells.add(cell);
	}
	
	/**
	 * Adds the cell at the index position. The cells row number is modified to match
	 * this row's row number
	 * @param cell - the cell
	 * @param index - the position of the cell in the list
	 */
	public void addCell(Cell cell, int index) {
		cell.setRowNumber(this.rowNumber);
		this.cells.add(index, cell);
	}
	
	public void removeCell(Cell cell) {
		this.cells.remove(cell);
	}
	
	public void removeCell(int index) {
		this.cells.remove(index);
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

}
