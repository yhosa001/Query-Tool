package in.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorResourceBundle{
	
	public String getMessage(String errorKey){
		
		ResourceBundle bundle = ResourceBundle.getBundle("in.resources.error_msg_en",Locale.ENGLISH);
		
		String message = bundle.getString(errorKey);		
		
		return message;
		
		
	}
}