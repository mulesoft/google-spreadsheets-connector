
package org.mule.module.google.spreadsheet.model.holders;

import java.util.List;
import javax.annotation.Generated;
import org.mule.module.google.spreadsheet.model.Cell;

@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-03-26T12:37:34-05:00", comments = "Build M4.1875.17b58a3")
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
