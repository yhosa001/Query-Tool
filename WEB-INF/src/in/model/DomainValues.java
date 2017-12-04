package in.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import in.hibernate.HibernatePlug;
import in.beans.Domain;
/**
 * 
 * @author yadhu
 *
 */
public class DomainValues{
	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	
	@SuppressWarnings("unchecked")
	public List<Domain> getDomainData() throws HibernateException{
		List<Domain> user = null;
		Query qry = session.createQuery("from Domain a");
		user = qry.list();
		return user;
	}
}