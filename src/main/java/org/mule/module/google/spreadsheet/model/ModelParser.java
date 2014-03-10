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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public abstract class ModelParser {

	
	public static List<Spreadsheet> parseSpreadsheet(SpreadsheetFeed feed) {
		return parseSpreadsheet(feed.getEntries());
	}
	
	public static List<Spreadsheet> parseSpreadsheet(List<SpreadsheetEntry> entries) {
		int size = entries.size();
		
		if (size > 0) {
			List<Spreadsheet> result = new ArrayList<Spreadsheet>(size);
			
			for (SpreadsheetEntry entry : entries) {
				result.add(new Spreadsheet(entry));
			}
			
			return result;
		}
		
		return new ArrayList<Spreadsheet>();
	}
	
	public static List<Worksheet> parseWorksheet(WorksheetFeed feed) {
		return parseWorksheet(feed.getEntries());
	}
	
	public static List<Worksheet> parseWorksheet(List<WorksheetEntry> entries) {
		int size = entries.size();
		
		if (size > 0) {
			List<Worksheet> result = new ArrayList<Worksheet>(size);
			
			for (WorksheetEntry entry : entries) {
				Worksheet ws = new Worksheet(entry);
				result.add(ws);
			}
			
			return result;
		}
		
		return new ArrayList<Worksheet>();
	}
	
	public static List<Row> parseCell(CellFeed feed) {
		return parseCell(feed.getEntries());
	}
	
	public static List<Row> parseCell(List<CellEntry> entries) {
		List<Row> result = new ArrayList<Row>();

		if (entries.size() > 0) {
			
			Map<Integer, Row> rows = new HashMap<Integer, Row>();

			for (CellEntry entry : entries) {
				
				int rowNumber = entry.getCell().getRow();
				Row row = rows.get(rowNumber);
				
				if (row == null)  {
					row = new Row();
					row.setRowNumber(rowNumber);
					rows.put(rowNumber, row);
					result.add(row);
				}
				
				row.addCell(parseCell(entry));
			}
			
		}
		return result;
	}
	
	public static Cell parseCell(CellEntry entry) {
		Cell myCell = new Cell();
		com.google.gdata.data.spreadsheet.Cell googleCell = entry.getCell();
		myCell.setColumnNumber(googleCell.getCol());
		myCell.setRowNumber(googleCell.getRow());
		myCell.setValueOrFormula(googleCell.getInputValue());
		myCell.setEvaluatedValue(googleCell.getValue());
		return myCell;
	}
	
	public static List<Row> parseRows(CellFeed feed) {
		return parseRows(feed.getEntries());
	}
	
	public static List<Row> parseRows(List<CellEntry> entries) {
		List<Row> retVal = new ArrayList<Row>();
		
		if (entries.isEmpty()) {
			return retVal;
		}
		
		Map<Integer, Row> rows = new HashMap<Integer, Row>();
		
		for (CellEntry entry : entries) {
			com.google.gdata.data.spreadsheet.Cell cell = entry.getCell();
			int rowNumber = cell.getRow();
			
			Row row = rows.get(rowNumber);
			if (row == null) {
				row = new Row();
				row.setRowNumber(rowNumber);
				rows.put(rowNumber, row);
				retVal.add(row);
			}
			
			Cell myCell = parseCell(entry);
			row.addCell(myCell);
		}
		
		Collections.sort(retVal);
		
		for (Row row : retVal) {
			row.sortCells();
		}
		return retVal;
		
	}
	
	public static String toCSV(List<Row> rows, String lineSeparator, String columnSeparator) {
		StringBuilder csv = new StringBuilder();
		
		for (Row row : rows) {
			int rowNumber = row.getRowNumber();
			
			for (Cell cell : row.getCells()) {
	
				if (csv.length() > 0) {
					csv.append(lineSeparator);
				}
				
				csv.append(rowNumber).append(columnSeparator)
					.append(cell.getColumnNumber()).append(columnSeparator)
					.append(cell.getEvaluatedValue());
			}
		}
		
		return csv.toString();
	}
	
}
