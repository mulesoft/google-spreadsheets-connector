
package org.mule.module.google.spreadsheet.model.holders;

import java.util.List;
import javax.annotation.Generated;
import org.mule.module.google.spreadsheet.model.Cell;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class RowExpressionHolder {

    protected Object rowNumber;
    protected int _rowNumberType;
    protected Object cells;
    protected List<Cell> _cellsType;

    /**
     * Sets rowNumber
     * 
     * @param value Value to set
     */
    public void setRowNumber(Object value) {
        this.rowNumber = value;
    }

    /**
     * Retrieves rowNumber
     * 
     */
    public Object getRowNumber() {
        return this.rowNumber;
    }

    /**
     * Sets cells
     * 
     * @param value Value to set
     */
    public void setCells(Object value) {
        this.cells = value;
    }

    /**
     * Retrieves cells
     * 
     */
    public Object getCells() {
        return this.cells;
    }

}
