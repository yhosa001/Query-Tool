package in.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionSupport;

import flexjson.JSONSerializer;
import in.model.ErrorResourceBundle;
import in.model.DomainDelete;
import in.beans.Domain;
import in.model.DomainRegister;
import in.model.DomainUpdate;
import in.model.DomainValues;

/**
 * @author yadhu
 */


public class DomainAction extends ActionSupport 
implements ServletRequestAware{


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	private String domainName;
	private String dbType;
	private String dbName;
	private String hostName;
	private String port;
	private String dbUser;
	private String dbPassword;
	
	
	public String getDomainValues(){
		List<Domain> domainList = null;
		String result = null;
		JSONSerializer serializer = new JSONSerializer();
		try{
			DomainValues values = new DomainValues();
			domainList = values.getDomainData();
		}catch(HibernateException e){
			System.out.println(e.getMessage());
		}
		result = serializer.serialize(domainList);
		request.setAttribute("result", result);
		return "result";
	}

	public String registerDomain(){
		HashMap<String,String> map = new HashMap<String, String>();
		String errorKey;
		String result = null;
		JSONSerializer serializer = new JSONSerializer();
		try{
			DomainRegister register = new DomainRegister();
			map = register.domainRegister(domainName,
					dbType, dbName, hostName, port, dbUser, dbPassword);
			String status= map.get("result");
			if (status.equals("error")) {
				errorKey=map.get("message");
				ErrorResourceBundle error=new ErrorResourceBundle();
				map.put("message", error.getMessage(errorKey));
			}
		}catch(HibernateException e){
			map.put("result", "error");
			map.put("message", e.getMessage());
		}
		result= serializer.serialize(map);
		request.setAttribute("result", result);
		return "result";
		
	}
	
	
	
	public String updateDomain(){
		HashMap<String, String> map= new HashMap<String, String>();
		JSONSerializer serializer = new JSONSerializer();
		String result = null;
		try{
			DomainUpdate update = new DomainUpdate();
			map = update.domainUpdate(domainName,
					dbType, dbName, hostName, port, dbUser, dbPassword);
			
		}catch(HibernateException e){
			map.put("result", "error");
			map.put("message", e.getMessage());
		
		}
		result = serializer.serialize(map);
		request.setAttribute("result", result);	
		return "result";
		
	}
	
	
	public String deleteDomain(){
		HashMap<String, String> map = new HashMap<String, String>();
		JSONSerializer serializer = new JSONSerializer();
		String result = null;
		try {
			DomainDelete delete = new DomainDelete();
			map = (HashMap<String, String>) delete.domainDelete(domainName);
		} catch (HibernateException e) {
			map.put("result", "error");
			map.put("message", e.getMessage());
		}
		result = serializer.serialize(map);
		request.setAttribute("result", result);
		return "result";
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletRequest getServletRequest(){
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
	 * @return the dbType
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * @param dbType
	 *            the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName
	 *            the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}

	/**
	 * @param dbUser
	 *            the dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword
	 *            the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

}