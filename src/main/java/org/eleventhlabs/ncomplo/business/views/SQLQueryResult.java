package org.eleventhlabs.ncomplo.business.views;

import java.util.ArrayList;
import java.util.List;



public class SQLQueryResult {

    private int columnCount = -1;
    private List<String> columnNames = null;
    private List<List<Object>> data = new ArrayList<List<Object>>();


    
    public SQLQueryResult() {
        super();
    }

    

    public List<String> getColumnNames() {
        return this.columnNames;
    }


    public void setColumnNames(final List<String> columnNames) {
        this.columnNames = columnNames;
        this.columnCount = this.columnNames.size();
    }
    
    
    public boolean hasMetaData() {
        return this.columnNames != null;
    }

    
    public List<List<Object>> getData() {
        return this.data;
    }


    public void addDataRow(final List<Object> dataRow) {
        this.data.add(dataRow);
    }


    public int getColumnCount() {
        return this.columnCount;
    }
    
    
    public int getRowCount() {
        return this.data.size();
    }
    
}
