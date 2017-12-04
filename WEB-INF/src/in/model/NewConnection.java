package in.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import in.beans.Domain;
import in.hibernate.HibernatePlug;
/**
 * 
 * @author yadhu
 *
 */
public class NewConnection{
	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	Statement statement = null;
	Connection connection = null;
	
	@SuppressWarnings("unchecked")
	public Connection getConnection(String domainName) 
			throws HibernateException, SQLException, IllegalStateException{
		
		List<Domain> user = null;
		Query qry = session.createQuery("from Domain a where a.Domain_Name=?");
		qry.setParameter(0, domainName);
		user = qry.list();
		String DBType = user.get(0).DB_Type;
		String HostName = user.get(0).Host_Name;
		Integer Port = user.get(0).Port;
		String Service = user.get(0).DB_Name;
		String UserName = user.get(0).DBUser_Name;
		String Password = user.get(0).DB_Password;
		String URL = "jdbc:" + DBType + ":thin:@" + HostName + ":" + Port + ":"+ Service;
		
		DataBaseConnection db = new DataBaseConnection();
		connection = db.dataBaseConn(URL, UserName, Password);
		
		
		
		return connection;
	}

	@SuppressWarnings("unchecked")
	public String checkDomain(String domainName)throws HibernateException {
		String domName = domainName;
		Query qry = session.createQuery("from Domain a where a.Domain_Name=?");
		qry.setParameter(0, domainName);
		List<String> domain = qry.list();
		Iterator<String> it = domain.iterator();
		if (!it.hasNext()) {
			domName = "domain not present";
		}
		return domName;
	}
	
}