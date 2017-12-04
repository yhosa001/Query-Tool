package in.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "History")
public class History {

	@Id
	@Column(name = "history_name")
	public String History_Name;

	@Column(name = "query")
	public String Query;
	@Column(name = "exec_user")
	public String UserID;
	@Column(name = "domain_name")
	public String Domain_Name;
	@Column(name = "table_names")
	public String Table_Names;

	/**
	 * @return the history_Name
	 */
	public String getHistory_Name() {
		return History_Name;
	}

	/**
	 * @param history_Name
	 *            the history_Name to set
	 */
	public void setHistory_Name(String history_Name) {
		History_Name = history_Name;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return Query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		Query = query;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return UserID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID) {
		UserID = userID;
	}

	/**
	 * @return the domain_Name
	 */
	public String getDomain_Name() {
		return Domain_Name;
	}

	/**
	 * @param domain_Name
	 *            the domain_Name to set
	 */
	public void setDomain_Name(String domain_Name) {
		Domain_Name = domain_Name;
	}

	/**
	 * @return the table_Names
	 */
	public String getTable_Names() {
		return Table_Names;
	}

	/**
	 * @param table_Names
	 *            the table_Names to set
	 */
	public void setTable_Names(String table_Names) {
		Table_Names = table_Names;
	}

}
