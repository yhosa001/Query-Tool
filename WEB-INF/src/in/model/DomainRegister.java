package in.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import in.hibernate.HibernatePlug;
import in.beans.Domain;
import in.model.Constants;

public class DomainRegister{
	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	
	@SuppressWarnings("unchecked")
	
	public HashMap<String, String> domainRegister(String domainName, String dbType, 
			String dbName, String hostName,
			String port, String dbUser,
			String dbPassword) {
		HashMap<String, String> result = new HashMap<String, String>();
		int portnum = Integer.parseInt(port);
		Query qry = session
					.createQuery("select b.Domain_Name from Domain b where b.Domain_Name=?");
		qry.setParameter(0, domainName);
		List<String> user = qry.list();
		Iterator<String> it = user.iterator();
		if (it.hasNext()) {
			result.put("result", "error");
			result.put("message", Constants.ERROR_027);
			return result;
		} else {

			Domain insert = new Domain();
			insert.setDomain_Name(domainName);
			insert.setDB_Type(dbType);
			insert.setDB_Name(dbName);
			insert.setHost_Name(hostName);
			insert.setPort(portnum);
			insert.setDBUser_Name(dbUser);
			insert.setDB_Password(dbPassword);
			org.hibernate.Transaction tx = session.beginTransaction();
			session.saveOrUpdate(insert);
			tx.commit();
			result.put("result", "success");
			result.put("message", "Domain Registered Successfully");
		}
		return result;
	}

}
