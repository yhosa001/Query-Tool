package in.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import in.model.Constants;
import in.hibernate.HibernatePlug;

/**
 * 
 * @author yadhu
 *
 */

public class LoginCheck {
	
	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> loginValidation(String userid, String password)
			throws HibernateException {
		
		HashMap<String, String> result = new HashMap<String, String>();
		Query qry = session.createQuery("select b.userid from User b where b.userid=?");
		qry.setParameter(0, userid);
		List<String> user = qry.list();
		Iterator<String> it = user.iterator();
		if(!it.hasNext()){
			result.put("result", "error");
			result.put("message", Constants.ERROR_025);
		}else{
			qry = session
					.createQuery("select b.userid from User b where b.userid=? and b.password=?");
			qry.setParameter(0, userid);
			qry.setParameter(1, password);
			List<String> userpass = qry.list();
			Iterator<String> ip = userpass.iterator();
			if (!ip.hasNext()) {
				result.put("result", "error");
				result.put("message", Constants.ERROR_026);
			} else {
				result.put("result", "success");
				result.put("message", "Login Success");
			}
			
		}
				
		return result;
	
	}
	
}
