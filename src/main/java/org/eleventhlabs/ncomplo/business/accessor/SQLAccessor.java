package org.eleventhlabs.ncomplo.business.accessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.eleventhlabs.ncomplo.business.views.SQLQueryResult;
import org.eleventhlabs.ncomplo.exceptions.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SQLAccessor {
    
    @Autowired
    private DataSource dataSource;

    
    public SQLAccessor() {
        super();
    }

    
    
    public int executeUpdate(final String sql) {
        
        Connection con = null;
        PreparedStatement st = null;
        try {
            
            con = this.dataSource.getConnection();
            st = con.prepareStatement(sql);
            
            return st.executeUpdate();
            
        } catch (final Exception e) {
            throw new InternalErrorException(e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (final Exception e) {
                    // ignored
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (final Exception e) {
                    // ignored
                }
            }
        }
        
    }

    

    
    
    public SQLQueryResult executeQuery(final String sql) {
        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            
            con = this.dataSource.getConnection();
            st = con.prepareStatement(sql);
            
            rs = st.executeQuery();
            
            final SQLQueryResult result = new SQLQueryResult();
            
            while (rs.next()) {
                
                if (!result.hasMetaData()) {
                    retrieveMetaData(result, rs);
                }

                final List<Object> dataRow = new ArrayList<Object>();

                for (int i = 1; i <= result.getColumnCount(); i++) {
                    dataRow.add(rs.getObject(i));
                }
                
                result.addDataRow(dataRow);
                
            }
            
            return result;
            
        } catch (final Exception e) {
            throw new InternalErrorException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final Exception e) {
                    // ignored
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final Exception e) {
                    // ignored
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (final Exception e) {
                    // ignored
                }
            }
        }
        
    }

    
    
    private void retrieveMetaData(final SQLQueryResult result, final ResultSet rs) 
            throws SQLException {
        
        final ResultSetMetaData metaData = rs.getMetaData();
        final int columnCount = metaData.getColumnCount();
        
        final List<String> columnNames = new ArrayList<String>();
        
        for (int i = 1; i <= columnCount;i++) {
            columnNames.add(metaData.getColumnLabel(i));
        }

        result.setColumnNames(columnNames);
        
    }
    
}
    