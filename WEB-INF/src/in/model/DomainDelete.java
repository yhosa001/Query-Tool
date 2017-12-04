package in.model;

import java.util.HashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.hibernate.HibernatePlug;

public class DomainDelete{

	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	
	public HashMap<String, String> domainDelete(String domainName) 
			throws HibernateException{
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		Transaction tx = session.beginTransaction();
		Query qry = session
				.createQuery("delete from Domain b where b.Domain_Name=?");
		qry.setParameter(0, domainName);
		qry.executeUpdate();
		tx.commit();
		System.out.println("Command successfully executed....");
		result.put("result", "success");
		result.put("message", "Deleted Successfully");
		return result;
	}
	
}