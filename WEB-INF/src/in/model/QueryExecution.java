package in.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.ini4j.Ini;


public class QueryExecution{

		
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int rowCount = 0;
	ServletOutputStream outStream = null;
	
	
	public StringBuffer downloadCSV(Connection connection, String query, 
			HttpServletResponse response) throws SQLException, IOException{
		
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		StringBuffer line = new StringBuffer();
		int numOfCols = result.getMetaData().getColumnCount();
		for (int i = 1; i < (numOfCols+1) ; i++){
			line.append(result.getMetaData().getColumnName(i));
			if(i<numOfCols)
				line.append(",");
			else
				line.append("\r\n");
		}
		
		int m=1;
		while (result.next()){
			for(int j=1; j<(numOfCols+1);j++){
				line.append(result.getString(j));
				
				if(j < numOfCols)
					line.append(",");
				else
					line.append("\r\n");
			}
			System.out.println("No of rows" + m);
			m++;
		}
		if(m == 1){
			StringBuffer line1 = new StringBuffer();
			line1.append("no rows Selected");
			return line1;
		} else {
			return line;
		}
	}


	public StringBuffer downloadText(Connection connection, String query, HttpServletResponse response) 
			throws SQLException, IOException{
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		StringBuffer line = new StringBuffer();
		int numOfCols = result.getMetaData().getColumnCount();
		
		for (int i = 1; i < (numOfCols+1) ; i++){
			line.append(result.getMetaData().getColumnName(i));
			if(i<numOfCols)
				line.append("\t");
			else
				line.append("\r\n");
		}
		
		int m=1;
		while (result.next()){
			for(int j=1; j<(numOfCols+1);j++){
				line.append(result.getString(j));
				
				if(j < numOfCols)
					line.append("\t");
				else
					line.append("\r\n");
			}
			System.out.println("No of rows" + m);
			m++;
		}
		if(m == 1){
			StringBuffer line1 = new StringBuffer();
			line1.append("no rows Selected");
			return line1;
		} else {
			return line;
		}
	}


	public List<ArrayList<String>> getResults(Connection connection2, String query, String tables, String domainName,
			String userID, String path, String count) throws HibernateException, Exception{
		String table;
		List<String> columnlist = new ArrayList<String>();
		List<String> listRecords = new ArrayList<String>();
		List<String> listSettings = new ArrayList<String>();
		List<String> totalNumOfRecords = new ArrayList<String>();
		List<String> columnCount = new ArrayList<String>();
		List<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();
		HistoryInsert insert = new HistoryInsert();
		statement = connection.createStatement();
		if (count!=null) {
			String sql = "select count(*) from (" .concat(query).concat( ")");
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				totalNumOfRecords.add(resultSet.getString(1));
			}
			outputList.add((ArrayList<String>) totalNumOfRecords);
		} else {
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				columnlist.add(md.getColumnName(i));
				System.out.println(md.getColumnName(i));
			}
			outputList.add((ArrayList<String>) columnlist);
			while (resultSet.next()) {
				rowCount++;
				for (int i = 1; i <= md.getColumnCount(); i++) {
					listRecords.add(resultSet.getString(i));
				}
			}
			outputList.add((ArrayList<String>) listRecords);
			Ini ini = new Ini(new File(path.concat("\\Settings.ini")));
			listSettings.add(ini.get("Settings", "NumOfRec"));
			String color = "#" + ini.get("Settings", "ThemeColor");
			listSettings.add(color);
			outputList.add((ArrayList<String>) listSettings);
			String Total = String.valueOf(rowCount);
			totalNumOfRecords.add(Total);
			outputList.add((ArrayList<String>) totalNumOfRecords);
			System.out.print(md.getColumnCount());
			if (tables != null) {
				/* generate TimeStamp */

				Date date = new Date();
				Timestamp currentTimestamp = new Timestamp(date.getTime());
				long TimeStamp = currentTimestamp.getTime();
				/* Get the Table Names */

				System.out.println(tables);
				String[] tableNames = tables.split(",");
				table = tableNames[0];
				System.out.println(table);

				// create the History name //
				String HistoryName = "history_" + TimeStamp + "[" + table + "]";
				System.out.print(HistoryName);
				int Values = insert.insertValues(HistoryName, query,
						domainName, tables, userID);
				System.out.println(Values);
			} else {
				String columnlength = "" + (md.getColumnCount());
				columnCount.add(columnlength);
				outputList.add((ArrayList<String>) columnCount);
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return outputList;
	}
}