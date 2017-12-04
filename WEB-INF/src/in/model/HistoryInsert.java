package in.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.hibernate.HibernatePlug;
import in.beans.History;

public class HistoryInsert{

	SessionFactory factory = HibernatePlug.getFactory();
	Session session = factory.openSession();
	
	@SuppressWarnings("unchecked")
	public int insertValues(String historyName, String query, 
			String domainName, String tables, String userID) 
					throws HibernateException, Exception{
		History insert = new History();
		Object History;
		List<History> user = null;
		int history;
		insert.setHistory_Name(historyName);
		insert.setQuery(query);
		insert.setUserID(userID);
		insert.setDomain_Name(domainName);
		insert.setTable_Names(tables);
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(insert);
		tx.commit();
		//Deleting the History Which Exceeds the NumberHistory length
		Query qry1 = session
				.createQuery("Select b.NumbeOfHistory from user1 b where b.userid=?");
		qry1.setParameter(0, userID);
		List<?> l = qry1.list();
		System.out.print(l.get(0));
		History = l.get(0);
		history = Integer.parseInt((String) History);
		Query qry = session
				.createQuery("from History as a where a.UserID=? order by a.History_Name desc");
		qry.setParameter(0, userID);
		user = qry.list();
		int i;
		int length = user.size();
		for (i = history; i < length; i++) {
			History pt = (History) user.get(i);
			String value = pt.getHistory_Name();
			Transaction t = session.beginTransaction();
			Query qry2 = session
					.createQuery("delete from History as a where a.History_Name=?");
			qry2.setParameter(0, value);
			qry2.executeUpdate();
			t.commit();
		}
		return 1;
	}
	
}