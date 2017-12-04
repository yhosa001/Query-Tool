package in.model;

import java.util.HashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.hibernate.HibernatePlug;
/**
 * 
 * @author yadhu
 *
 */
public class DomainUpdate{
	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();

	
	public HashMap<String, String> domainUpdate(String domainName, String dbType, String dbName, String hostName,
			String port, String dbUser, String dbPassword) throws HibernateException {
		
		HashMap<String, String> result = new HashMap<String, String>();
		int portnum = Integer.parseInt(port);
		// Update the values to the Domain Table //
		Transaction tx = session.beginTransaction();
		Query qry = session
				.createQuery("update from Domain b set b.DB_Name=?,b.Host_Name=?,"
						+ "b.Port=?,DBUser_Name=?,b.DB_Password=? where b.Domain_Name=?");
		qry.setParameter(0, dbName);
		qry.setParameter(1, hostName);
		qry.setParameter(2, portnum);
		qry.setParameter(3, dbUser);
		qry.setParameter(4, dbPassword);
		qry.setParameter(5, domainName);
		qry.executeUpdate();
		tx.commit();
		result.put("result", "success");
		result.put("message", "Updated Successfully");
		return result;
	}
	
}