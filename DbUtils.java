package user;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class DbUtils {
	public static TableModel resultSetToTableModel(ResultSet rs) {
		try {
			ResultSetMetaData metadata=rs.getMetaData();
			int numberofcolumns=metadata.getColumnCount();
			Vector columnNames=new Vector();
			
			//get column names
			for(int column=0;column<numberofcolumns;column++) {
				columnNames.addElement(metadata.getColumnLabel(column+1));
			}
			//get rows
			Vector rows=new Vector();
			while(rs.next()) {
				Vector newRow= new Vector();
				for(int i=1;i<=numberofcolumns;i++) {
					newRow.addElement(rs.getObject(i));
				}
				rows.addElement(newRow);
			}
			return new DefaultTableModel(rows,columnNames);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
