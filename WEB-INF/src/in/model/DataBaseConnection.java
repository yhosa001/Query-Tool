package in.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author yadhu
 *
 */
public class DataBaseConnection{
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public Connection dataBaseConn(String URL, String USER, String PASS) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(URL,USER,PASS);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return connection;
	}
	
}