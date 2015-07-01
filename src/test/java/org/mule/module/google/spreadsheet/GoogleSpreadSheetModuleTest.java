/**
 * Mule Google Spreadsheets Cloud Connector
 * <p/>
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * <p/>
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 * <p/>
 * This file was automatically generated by the Mule Development Kit
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.module.google.spreadsheet;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.CellQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.client.spreadsheet.WorksheetQuery;
import com.google.gdata.data.IFeed;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.ServiceException;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mule.module.google.spreadsheet.model.Cell;
import org.mule.module.google.spreadsheet.model.Row;
import org.mule.module.google.spreadsheet.model.Spreadsheet;
import org.mule.module.google.spreadsheet.model.Worksheet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Test case for the module itself.
 * The goal of this test is to merely test the connector's code. It assumes that the google client works,
 * reason why I use mockito to mock it.
 *
 * Thus, this test can be ran without actually hiting any google server.
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class GoogleSpreadSheetModuleTest extends TestCase {

    private static final String CONSUMER_KEY = "CONSUMER_KEY";
    private static final String CONSUMER_SECRET = "CONSUMER_SECRET";
    private static final String SPREADSHEET_NAME = "Test Spreadsheet";
    private static final String WORKSHEET_NAME = "Test Worksheet";
    private static final String TEST_URL = "http://www.muletest.com/cellFeed";
    private static final String APPLICATION_NAME = "APPLICATION_NAME";

    private GoogleSpreadSheetConnector connector;
    private SpreadsheetService ss;
    private DocsService docsService;

    private SpreadsheetFeed testSpreadsheet;
    private WorksheetFeed testWorksheet;
    private CellFeed testCellFeed;

    @Test
    public void testGetCellRange() throws Exception {
        final int minRow = 1;
        final int maxRow = 4;
        final int minCol = 1;
        final int maxCol = 2;

        List<Row> rows = this.connector.getCellRange(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, minRow, maxRow, minCol, maxCol);

        this.assertRows(rows);
    }

    @Test
    public void testGetCellRangeAsCSV() throws Exception {
        final int minRow = 1;
        final int maxRow = 4;
        final int minCol = 1;
        final int maxCol = 2;

        String csv = this.connector.getCellRangeAsCsv(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, ",", "\n", minRow, maxRow, minCol, maxCol);
        this.assertCsv(csv);
    }

    //@Todo: Review and fix the tests.
    @Test
    public void testSpreadsheetQuery() throws Exception {
        when(this.ss.query(any(SpreadsheetQuery.class), eq(SpreadsheetFeed.class))).thenAnswer(new Answer<SpreadsheetFeed>() {

            @Override
            public SpreadsheetFeed answer(InvocationOnMock invocation) throws Throwable {
                SpreadsheetQuery query = (SpreadsheetQuery) invocation.getArguments()[0];
                assertEquals("query title mismatch", SPREADSHEET_NAME, query.getTitleQuery());
                return testSpreadsheet;
            }
        });

        this.connector.getWorksheetByTitle(SPREADSHEET_NAME, WORKSHEET_NAME, 0);
        verify(this.ss, times(1)).query(any(SpreadsheetQuery.class), eq(SpreadsheetFeed.class));
    }

    @Test
    public void testWorksheetQuery() throws Exception {
        when(this.ss.query(any(WorksheetQuery.class), eq(WorksheetFeed.class))).thenAnswer(new Answer<WorksheetFeed>() {

            @Override
            public WorksheetFeed answer(InvocationOnMock invocation) throws Throwable {
                WorksheetQuery query = (WorksheetQuery) invocation.getArguments()[0];
                assertEquals("query title mismatch", WORKSHEET_NAME, query.getTitleQuery());
                return testWorksheet;
            }
        });

        this.connector.getWorksheetByTitle(SPREADSHEET_NAME, WORKSHEET_NAME, 0);
        verify(this.ss, times(1)).query(any(WorksheetQuery.class), eq(WorksheetFeed.class));
    }

    @Test
    public void testCellQuery() throws IOException, ServiceException {
        final int minRow = 1;
        final int maxRow = 4;
        final int minCol = 1;
        final int maxCol = 2;

        when(this.ss.query(any(CellQuery.class), eq(CellFeed.class))).thenAnswer(new Answer<CellFeed>() {

            @Override
            public CellFeed answer(InvocationOnMock invocation) throws Throwable {
                CellQuery query = (CellQuery) invocation.getArguments()[0];
                assertTrue("min row mismatch", minRow == query.getMinimumRow());
                assertTrue("max row mismatch", maxRow == query.getMaximumRow());
                assertTrue("min col mismatch", minCol == query.getMinimumCol());
                assertTrue("max col mismatch", maxCol == query.getMaximumCol());

                return testCellFeed;
            }
        });

        this.connector.getCellRange(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, minRow, maxRow, minCol, maxCol);
        verify(this.ss, times(1)).query(any(CellQuery.class), eq(CellFeed.class));
    }

    @Test
    public void testGetAllCells() throws Exception {
        List<Row> rows = this.connector.getAllCells(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);
        this.assertRows(rows);
    }

    @Test
    public void testGetAllCellsAsCSV() throws Exception {
        String csv = this.connector.getAllCellsAsCsv(SPREADSHEET_NAME, WORKSHEET_NAME, ",", "\n", 0, 0);
        this.assertCsv(csv);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testCreateSpreadsheet() throws Exception {
        final String title = "mock spreadsheet";

        when(this.docsService.insert(any(URL.class), any(com.google.gdata.data.docs.SpreadsheetEntry.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                com.google.gdata.data.docs.SpreadsheetEntry entry = (com.google.gdata.data.docs.SpreadsheetEntry) invocation.getArguments()[1];
                assertEquals("spreasheet title not as expected", title, entry.getTitle().getPlainText());
                return null;
            }
        });
        this.connector.createSpreadsheet(title);
        verify(this.docsService, times(1)).insert(any(URL.class), any(com.google.gdata.data.docs.SpreadsheetEntry.class));
    }

    @Test
    public void testGetAllSpreadsheets() throws Exception {
        when(this.ss.getFeed(any(URL.class), eq(SpreadsheetFeed.class))).thenReturn(this.testSpreadsheet);
        List<Spreadsheet> spreadsheets = this.connector.getAllSpreadsheets();
        this.assertSpreadsheets(spreadsheets);
    }

    @Test
    public void testGetAllWorksheets() throws Exception {
        List<Worksheet> ws = this.connector.getAllWorksheets(SPREADSHEET_NAME, 0);
        this.assertWorksheets(ws);
    }

    @Test
    public void testCreateWorksheet() throws Exception {
        final int rowCount = 5;
        final int columnCount = 10;

        when(this.ss.insert(any(URL.class), any(WorksheetEntry.class))).thenAnswer(new Answer<WorksheetEntry>() {

            @Override
            public WorksheetEntry answer(InvocationOnMock invocation) throws Throwable {
                WorksheetEntry entry = (WorksheetEntry) invocation.getArguments()[1];
                assertEquals("unexpected title", entry.getTitle().getPlainText(), WORKSHEET_NAME);
                assertEquals("unexpected rowCount", entry.getRowCount(), rowCount);
                assertEquals("unexpected columnCount", entry.getColCount(), columnCount);
                return entry;
            }
        });

        this.connector.createWorksheet(SPREADSHEET_NAME, 0, WORKSHEET_NAME, rowCount, columnCount);
        verify(this.ss, times(1)).insert(any(URL.class), any(WorksheetEntry.class));
    }

    @Test
    public void testDeleteWorksheet() throws Exception {
        WorksheetEntry entry = this.testWorksheet.getEntries().get(0);
        this.connector.deleteWorksheet(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);
        verify(entry, times(1)).delete();
    }

    @Test
    public void testUpdateWorksheetMetadata() throws Exception {
        final String title = "test title";
        final boolean draft = false;
        final boolean canEdit = false;
        final String summary = "test summary";
        final int rowCount = 10;
        final int colCount = 15;

        WorksheetEntry entry = new WorksheetEntry();
        WorksheetEntry spy = spy(entry);
        Mockito.doReturn(entry).when(spy).update();

        this.testWorksheet.getEntries().clear();
        this.testWorksheet.getEntries().add(spy);

        this.connector.updateWorksheetMetadata(SPREADSHEET_NAME, WORKSHEET_NAME, title, draft, canEdit, summary, rowCount, colCount, 0, 0);

        verify(spy).update();

        assertEquals("unexpected title", entry.getTitle().getPlainText(), title);
        assertFalse("unexpected draft", entry.isDraft());
        assertEquals("unexpected summary", entry.getSummary().getPlainText(), summary);
        assertEquals("unexpected row count", entry.getRowCount(), rowCount);
        assertEquals("unexpected col count", entry.getColCount(), colCount);
    }

    @Test
    public void testSetRowValuesWithPurge() throws Exception {
        List<Row> rows = new ArrayList<Row>();
        rows.add(new Row());
        this.connector.setRowValues(rows, SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, true);
        verify(this.connector).purgeWorksheet(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);
    }

    @Test
    public void testSetRowValuesWithoutPurge() throws Exception {
        List<Row> rows = new ArrayList<Row>();
        rows.add(new Row());

        this.connector.setRowValues(rows, SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, false);
        verify(this.connector, Mockito.never()).purgeWorksheet(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);
    }

    @Test
    public void testSetRowValues() throws Exception {
        final List<Row> rows = new ArrayList<Row>();
        rows.add(new Row());
        this.connector.setRowValues(rows, SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0, false);
        verify(this.ss, times(1)).batch(any(URL.class), any(IFeed.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSetCsvValues() throws Exception {
        Mockito.doNothing().when(this.connector).
                setRowValues(any(List.class), anyString(), anyString(), anyInt(), anyInt(), Matchers.anyBoolean());

        this.connector.setCsvValues(SPREADSHEET_NAME, WORKSHEET_NAME, "csv", 0, 0, "\n", ",", 0, 0, false);
        verify(this.connector, times(1)).setRowValues(any(List.class), eq(SPREADSHEET_NAME), eq(WORKSHEET_NAME), eq(0), eq(0), eq(false));
    }

    @Test
    public void testGetAuthors() throws Exception {
        SpreadsheetEntry entry = this.testSpreadsheet.getEntries().get(0);
        this.connector.getAuthors(SPREADSHEET_NAME, 0);
        verify(entry, times(1)).getAuthors();
    }

    @Test
    public void testGetColumnHeaders() throws Exception {
        Row row = this.connector.getColumnHeaders(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);
        assertNotNull(row);
        List<Cell> cells = row.getCells();
        String[] columns = this.loadTestDataLines()[0].split(",");
        assertTrue("unexpected number of cells", cells.size() == columns.length);

        for (int i = 0; i < columns.length; i++) {
            assertEquals("unexpected cell value", cells.get(i).getEvaluatedValue(), columns[i]);
        }
    }

    @Test
    public void testPurgeWorksheet() throws Exception {
        this.connector.purgeWorksheet(SPREADSHEET_NAME, WORKSHEET_NAME, 0, 0);

        for (CellEntry entry : this.testCellFeed.getEntries()) {
            verify(entry, times(1)).delete();
        }
    }

    @Test
    public void testSearch() throws Exception {
        final String q = "test query";
        final String match = "match";
        when(this.ss.query(any(CellQuery.class), eq(CellFeed.class))).thenAnswer(new Answer<CellFeed>() {

            @Override
            public CellFeed answer(InvocationOnMock invocation) throws Throwable {
                CellQuery query = (CellQuery) invocation.getArguments()[0];
                assertEquals("another query was expected", query.getFullTextQuery(), q);

                testCellFeed.getEntries().clear();
                testCellFeed.getEntries().add(newCellEntry(0, 0, match));
                return testCellFeed;
            }
        });

        List<Row> rows = this.connector.search(SPREADSHEET_NAME, WORKSHEET_NAME, q, 0, 0);
        assertTrue("only one row was expected", rows.size() == 1);
        List<Cell> cells = rows.get(0).getCells();
        assertTrue("only one cell was expected", cells.size() == 1);
        assertEquals("another cell value was expected", cells.get(0).getEvaluatedValue(), match);
    }

    private void assertCsv(String csv) {
        List<String> expected = new ArrayList<String>();
        String[] testLines = this.loadTestDataLines();

        for (int i = 0; i < testLines.length; i++) {
            String line = testLines[i];
            String[] columns = line.split(",");
            for (int j = 0; j < columns.length; j++) {
                expected.add((i + 1) + "," + (j + 1) + "," + columns[j]);
            }
        }
        String[] lines = csv.split("\n");

        assertTrue("unexpected line numbers", lines.length == expected.size());
        for (int i = 0; i < lines.length; i++) {
            assertEquals("unexpected line content", lines[i], expected.get(i));
        }
    }

    private void assertSpreadsheets(List<Spreadsheet> spreadsheets) {
        assertEquals("number of spreadsheets spreadsheets not as expected", spreadsheets.size(), this.testSpreadsheet.getEntries().size());
        int ssCount = spreadsheets.size();
        List<SpreadsheetEntry> entries = this.testSpreadsheet.getEntries();

        for (int i = 0; i < ssCount; i++) {
            Spreadsheet ss = spreadsheets.get(i);
            assertEquals("Spreadsheet name not as expected", ss.getTitle(), entries.get(i).getTitle().getPlainText());
        }
    }

    private void assertWorksheets(List<Worksheet> worksheets) {
        assertEquals("number of worksheets spreadsheets not as expected", worksheets.size(), this.testWorksheet.getEntries().size());
        int wsCount = worksheets.size();
        List<WorksheetEntry> entries = this.testWorksheet.getEntries();

        for (int i = 0; i < wsCount; i++) {
            Worksheet ws = worksheets.get(i);
            assertEquals("worksheet name not as expected", ws.getTitle(), entries.get(i).getTitle().getPlainText());
        }
    }

    private void assertRows(List<Row> rows) {
        String[] lines = this.loadTestDataLines();
        assertTrue("expected " + lines.length + " rows", rows.size() == lines.length);

        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);
            assertTrue("Unexpected row number", row.getRowNumber() == i + 1);
            String[] columns = lines[i].split(",");

            List<Cell> cells = row.getCells();
            assertTrue("Expected " + columns.length + " cells", columns.length == cells.size());

            for (int j = 0; j < cells.size(); j++) {
                Cell cell = cells.get(j);
                assertTrue("unexpected row number", cell.getRowNumber() == row.getRowNumber());
                assertTrue("unexpected column number", cell.getColumnNumber() == j + 1);
                assertEquals("unexpected evaluated value", cell.getEvaluatedValue(), columns[j]);
                assertEquals("unexpected formula or value", cell.getValueOrFormula(), columns[j]);
            }
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.initTestWorksheet();
        this.initTestSpreadsheet();
        this.initTestCellFeed();

        this.ss = mock(SpreadsheetService.class);
        this.docsService = mock(DocsService.class);

        this.connector = spy(new GoogleSpreadSheetConnector());
        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                GoogleSpreadSheetConnector connector = (GoogleSpreadSheetConnector) invocation.getMock();
                connector.setSpreadsheetService(ss);
                connector.setDocService(docsService);

                return null;
            }
        }).when(this.connector).postAuthorization();

        // mock spreadsheet service
        this.connector.setConsumerKey(CONSUMER_KEY);
        this.connector.setConsumerSecret(CONSUMER_SECRET);
        this.connector.setApplicationName(APPLICATION_NAME);
        this.connector.postAuthorization();

        //mock queries
        when(this.ss.query(any(SpreadsheetQuery.class), eq(SpreadsheetFeed.class))).thenReturn(this.testSpreadsheet);
        when(this.ss.query(any(WorksheetQuery.class), eq(WorksheetFeed.class))).thenReturn(this.testWorksheet);
        when(this.ss.query(any(CellQuery.class), eq(CellFeed.class))).thenReturn(this.testCellFeed);
        when(this.ss.getFeed(any(URL.class), eq(CellFeed.class))).thenReturn(this.testCellFeed);
    }

    private void initTestSpreadsheet() throws Exception {
        this.testSpreadsheet = mock(SpreadsheetFeed.class);

        List<SpreadsheetEntry> entries = new ArrayList<SpreadsheetEntry>();
        SpreadsheetEntry entry = mock(SpreadsheetEntry.class);
        when(entry.getTitle()).thenReturn(new PlainTextConstruct(SPREADSHEET_NAME));
        when(entry.getWorksheetFeedUrl()).thenReturn(new URL("http://www.muletest.com/worksheetFeed"));
        List<WorksheetEntry> wsEntries = this.testWorksheet.getEntries();
        when(entry.getWorksheets()).thenReturn(wsEntries);
        entries.add(entry);

        when(this.testSpreadsheet.getEntries()).thenReturn(entries);
    }

    private void initTestWorksheet() throws Exception {
        this.testWorksheet = mock(WorksheetFeed.class);
        List<WorksheetEntry> entries = new ArrayList<WorksheetEntry>();

        WorksheetEntry entry = mock(WorksheetEntry.class);
        when(entry.getTitle()).thenReturn(new PlainTextConstruct(WORKSHEET_NAME));
        when(entry.getCellFeedUrl()).thenReturn(new URL(TEST_URL));
        entries.add(entry);

        when(this.testWorksheet.getEntries()).thenReturn(entries);
    }

    private void initTestCellFeed() throws Exception {
        this.testCellFeed = mock(CellFeed.class);
        List<CellEntry> entries = new ArrayList<CellEntry>();

        String[] lines = this.loadTestDataLines();

        for (int i = 0; i < lines.length; i++) {
            String[] cells = lines[i].split(",");
            for (int j = 0; j < cells.length; j++) {
                entries.add(newCellEntry(i + 1, j + 1, cells[j]));
            }
        }

        when(this.testCellFeed.getEntries()).thenReturn(entries);

        Link link = mock(Link.class);
        when(link.getHref()).thenReturn(TEST_URL);
        when(this.testCellFeed.getLink(anyString(), anyString())).thenReturn(link);
    }

    private String[] loadTestDataLines() {
        try {
            InputStream in = this.getClass().getResourceAsStream("/delimited.csv");
            String lines[] = IOUtils.toString(in).split("\n");
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CellEntry newCellEntry(int row, int column, String value) {
        com.google.gdata.data.spreadsheet.Cell googleCell = mock(com.google.gdata.data.spreadsheet.Cell.class);
        when(googleCell.getRow()).thenReturn(row);
        when(googleCell.getCol()).thenReturn(column);
        when(googleCell.getValue()).thenReturn(value);
        when(googleCell.getInputValue()).thenReturn(value);

        CellEntry entry = mock(CellEntry.class);
        when(entry.getCell()).thenReturn(googleCell);

        return entry;
    }

}
