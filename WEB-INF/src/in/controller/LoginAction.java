package in.controller;
/**
 * @author yadhu
 */

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import in.model.LoginCheck;
import in.model.EncryptPassword;
import in.model.ErrorResourceBundle;
import flexjson.JSONSerializer;

import org.hibernate.HibernateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginAction  extends ActionSupport implements ServletRequestAware, 
ServletResponseAware {


	HttpServletRequest request;
	HttpSession session = null;
	
	private static final long serialVersionUID = 1L;
	private String userid;
	private String password;
	
	private HttpServletResponse response = null;
	
	private PrintWriter out = null;
	
	public String loginCheck(){
		
		HashMap <String,String> map = new HashMap<String,String>();
		try{
			LoginCheck validation = new LoginCheck();
			EncryptPassword encrypt = new EncryptPassword();
			String encryptedPassword = encrypt.encryptPassword(password);
			map = validation.loginValidation(userid, encryptedPassword);
			System.out.println(encryptedPassword);
			String status = map.get("result");
			if(status.equals("success")){
				session = request.getSession();
				session.setAttribute("UserID",userid);
			}else{
				String errorKey = map.get("message");
				ErrorResourceBundle error = new ErrorResourceBundle();
				String errorMessage = error.getMessage(errorKey);
				map.put("message", errorMessage);
			}
			
			
		}catch (NoSuchAlgorithmException e) {
			map.put("result", "error");
			map.put("message", e.getMessage());
		}catch (HibernateException e) {
			map.put("result", "error");
			map.put("message", e.getMessage());
		} 
		
		
		JSONSerializer serializer = new JSONSerializer();
		String result = serializer.serialize(map);
		request.setAttribute("result",result);
		//result.jsp(${result} name has to matched). Resquest Scope; Response scope: response.setAttribute
		return "result";//sturts.xml(name)
	}
	
	
	/**
	 * it is used to clear the session when logout
	 */
	public void clearSession(){
		request.getSession().invalidate();
		String clear = "success";
		try{
			out = response.getWriter();
			out.print(clear);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	- The type LoginAction must implement the inherited abstract method 
	 ServletResponseAware.setServletResponse(HttpServletResponse)
	- The type LoginAction must implement the inherited abstract method 
	 ServletRequestAware.setServletRequest(HttpServletRequest)
	
	 *
	 *
	 *
	 	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	*/
	

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
		
	public String getUserid(){
		return userid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
