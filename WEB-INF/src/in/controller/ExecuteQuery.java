package in.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionSupport;

import flexjson.JSONSerializer;
import in.model.Constants;
import in.model.ErrorResourceBundle;
import in.model.NewConnection;
import in.model.QueryExecution;


/**
 * 
 * @author yadhu
 *
 */
public class ExecuteQuery extends ActionSupport implements ServletRequestAware,
ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection connection = null;
	private String domainName;
	private String query;
	private String tables;
	private String count;
	
	
	JSONSerializer serializer = new JSONSerializer();
	
	
	private HttpServletResponse response = null;
	HttpServletRequest request;

	
	public void formatDownload(){
		StringBuffer line = new StringBuffer();
		String Download = null;
		String errorKey;
		try{
			QueryExecution value = new QueryExecution();
			NewConnection connect = new NewConnection();
			ErrorResourceBundle error = new ErrorResourceBundle();
			connection = connect.getConnection(domainName);
			if(connection == null){
				errorKey = Constants.ERROR_029;
				line.append(error.getMessage(errorKey).concat(domainName));
				response.setContentType("text/html");
				response.setHeader("Content-Disposition","attachment; filename=\"Exception.text\"");
			} else {
				String format = request.getParameter("Format");
				String export = request.getParameter("Export");
				System.out.print(format);
				try {
					if (export == null) {
						if (format.equals("CSV")) {
							Download = "CSV";
							line = value.downloadCSV(connection, query,
									response);
						}
						if (format.equals("TEXT")) {
							Download = "TEXT";
							line = value.downloadText(connection, query,
									response);
						}
					} else {
						if (export.equals("CSV")) {
							Download = "CSV";
							line = value.downloadCSV(connection, query,
									response);
						}
						if (export.equals("TEXT")) {
							Download = "TEXT";
							line = value.downloadText(connection, query,
									response);
						}
					}
					if (Download.equals("CSV")) {
						String name = fileName();
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition",
								"attachment; filename=Q-" + name + ".csv");
					}
					if (Download.equals("TEXT")) {
						String name = fileName();
						response.setContentType("text/html");
						response.setHeader("Content-Disposition",
								"attachment; filename=Q-" + name + ".text");
					}

				} catch (SQLException e) {
					line.append(e.getMessage());
					response.setContentType("text/html");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"Exception.text\"");
				}
			}
		} catch (IOException e) {
			line.append(e.getMessage());
		} catch (IllegalStateException e) {
			line.append(e.getMessage());
		} catch (HibernateException e) {
			line.append(e.getMessage());
		} catch (SQLException e) {
			line.append(e.getMessage());
		} finally {
			try {
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(line.toString().getBytes());
				outputStream.flush();
				outputStream.close();
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getResult(){
		HttpSession session = request.getSession();
		String UserID = (String) session.getAttribute("UserID");
		String path = session.getServletContext().getRealPath("/");
		HashMap<String, Object> output = new HashMap<String, Object>();
		String errorKey;
		
		if(UserID == null){
			output.put("reult", "sessionTimeout");
		}else{
			List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			try {
				QueryExecution value = new QueryExecution();
				NewConnection connect = new NewConnection();
				String domainCheck = connect.checkDomain(domainName);
				if (domainCheck.equals("domain not present")) {
					output.put("result", "error");
					errorKey = Constants.ERROR_028;
					ErrorResourceBundle error=new ErrorResourceBundle();
					output.put("message", domainName.concat(error.getMessage(errorKey)));
				}else{
					connection = connect.getConnection(domainName);
					if (connection == null) {
						output.put("result", "error");
						errorKey = Constants.ERROR_029;
						ErrorResourceBundle error=new ErrorResourceBundle();
						output.put("message", error.getMessage(errorKey).concat(domainName));
					}else{
						list = value.getResults(connection, query, tables,
								domainName, UserID, path,count);
						output.put("result", "success");
						output.put("message", "success");
						output.put("resultlist", "" + list);
						System.out.println(list);
					}
				}
			}catch (IOException e) {
				output.put("result", "error");
				output.put("message", e.getMessage());
			} catch (SQLException e) {
				output.put("result", "error");
				output.put("message", e.getMessage());
			} catch (HibernateException e) {
				output.put("result", "error");
				output.put("message", e.getMessage());
			} catch (Exception e) {
				output.put("result", "error");
				output.put("messa ge", e.getMessage());
			} finally {
				try {
					String result = serializer.serialize(output);
					request.setAttribute("result", result);
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "result";
	}
	
	
	public String fileName() {
		Date date = new Date();
		Timestamp currentTimestamp = new Timestamp(date.getTime());
		long TimeStamp = currentTimestamp.getTime();
		String name = "" + TimeStamp;
		return name;
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
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
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the tables
	 */
	public String getTables() {
		return tables;
	}

	/**
	 * @param tables
	 *            the tables to set
	 */
	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

}