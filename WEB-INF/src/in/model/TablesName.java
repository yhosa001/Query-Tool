package in.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.beans.Tables;

public class TablesName{
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public List<Tables> getTables(Connection connection) throws SQLException {
			List<Tables> list = new ArrayList<Tables>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select Tname from tab");
			while(resultSet.next()){
				Tables item = new Tables();
				item.setTables(resultSet.getString("Tname"));
				list.add(item);
			}
		return list;
	}
	public List<Tables> getColumns(Connection connection, String tableName) {
		List<Tables> list = new ArrayList<Tables>();
		try {
			DatabaseMetaData metadata = connection.getMetaData();
			ResultSet resultSet = metadata.getColumns(null, null, tableName,
					null);
			while (resultSet.next()) {
				Tables item = new Tables();
				item.setColumns(resultSet.getString("COLUMN_NAME"));
				item.setType(resultSet.getString("TYPE_NAME"));
				list.add(item);
			}
		} catch (SQLException ea) {
			System.out.print(ea.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}