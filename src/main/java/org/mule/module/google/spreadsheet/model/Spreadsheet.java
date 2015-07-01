/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.google.spreadsheet.model;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class Spreadsheet extends Entry<SpreadsheetEntry> {

    private int index = 0;

    public Spreadsheet() {
        this(new SpreadsheetEntry());
    }

    public Spreadsheet(SpreadsheetEntry delegate) {
        super(delegate);
    }

    public List<Worksheet> getWorksheets() throws IOException, ServiceException {
        List<WorksheetEntry> entries = this.delegate().getWorksheets();
        List<Worksheet> retVal = new ArrayList<Worksheet>(entries.size());

        for (WorksheetEntry entry : entries) {
            retVal.add(new Worksheet(entry));
        }

        return retVal;
    }

    public String getTitle() {
        return this.delegate().getTitle().getPlainText();
    }

    public void setTitle(String v) {
        this.delegate().setTitle(TextConstruct.plainText(v));
    }

    public Date getUpdated() {
        return new Date(this.delegate().getUpdated().getValue());
    }

    public void setUpdated(Date v) {
        this.delegate().setUpdated(new DateTime(v));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
