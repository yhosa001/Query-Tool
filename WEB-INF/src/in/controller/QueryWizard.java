package in.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionSupport;

import flexjson.JSONSerializer;
import in.beans.Tables;
import in.model.TablesName;
import in.model.Constants;
import in.model.ErrorResourceBundle;
import in.model.NewConnection;
/**
 * 
 * @author yadhu
 *
 */
public class QueryWizard extends ActionSupport implements ServletRequestAware{
	
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpSession session = null;
	Statement statement = null;
	Connection connection = null;
	JSONSerializer serializer = new JSONSerializer();
	
	private PrintWriter out = null;
	
	private String domainName;
	private String tableName;
	
	public String getTables(){
		HashMap<String, String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String UserID = (String) session.getAttribute("UserID");
		String errorKey;
		if(UserID == null){
			map.put("result", "error");
		}else {
			try{
				NewConnection connect = new NewConnection();
				ErrorResourceBundle error = new ErrorResourceBundle();
				connection = connect.getConnection(domainName);
				if(connection == null){
					map.put("result", "error");
					errorKey = Constants.ERROR_029;
					map.put("message", error.getMessage(errorKey).concat(
							domainName));
					System.out.println(map);
					String list1 = serializer.serialize(map);
					System.out.println(list1);
					out.println(list1);
				}else{
					TablesName table = new TablesName();
					List<Tables> list = new ArrayList<Tables>();
					list = table.getTables(connection);
					String result = serializer.serialize(list);
					request.setAttribute("result", result);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return "result";
		
	}
	
	
	
	public String getColumns() {
		String result = "";
		List<Tables> list = new ArrayList<Tables>();
		//List is a reference interface
		//Arraylist class which implements List
		try {
			NewConnection connect = new NewConnection();
			connection = connect.getConnection(domainName);
			TablesName table = new TablesName();
			list = table.getColumns(connection, tableName);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		result = serializer.serialize(list);
		request.setAttribute("result", result);
		return "result";
	}

	
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName
	 *            the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}