package in.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Domain")
public class Domain{
	@Id
	@Column(name = "Domain_Name")
	public String Domain_Name;

	@Column(name = "DB_Type")
	public String DB_Type;
	@Column(name = "DB_Name")
	public String DB_Name;
	@Column(name = "Host_Name")
	public String Host_Name;
	@Column(name = "Port")
	public Integer Port;
	@Column(name = "DBUser_Name")
	public String DBUser_Name;
	@Column(name = "DB_Password")
	public String DB_Password;

	/**
	 * @return the domain_Name
	 */
	public String getDomain_Name() {
		return Domain_Name;
	}

	/**
	 * @param domain_Name the domain_Name to set
	 */
	public void setDomain_Name(String domain_Name) {
		Domain_Name = domain_Name;
	}

	/**
	 * @return the dB_Type
	 */
	public String getDB_Type() {
		return DB_Type;
	}

	/**
	 * @param type the dB_Type to set
	 */
	public void setDB_Type(String type) {
		DB_Type = type;
	}

	/**
	 * @return the dB_Name
	 */
	public String getDB_Name() {
		return DB_Name;
	}

	/**
	 * @param name the dB_Name to set
	 */
	public void setDB_Name(String name) {
		DB_Name = name;
	}

	/**
	 * @return the host_Name
	 */
	public String getHost_Name() {
		return Host_Name;
	}

	/**
	 * @param host_Name the host_Name to set
	 */
	public void setHost_Name(String host_Name) {
		Host_Name = host_Name;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return Port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		Port = port;
	}

	/**
	 * @return the dBUser_Name
	 */
	public String getDBUser_Name() {
		return DBUser_Name;
	}

	/**
	 * @param user_Name the dBUser_Name to set
	 */
	public void setDBUser_Name(String user_Name) {
		DBUser_Name = user_Name;
	}

	/**
	 * @return the dB_Password
	 */
	public String getDB_Password() {
		return DB_Password;
	}

	/**
	 * @param password the dB_Password to set
	 */
	public void setDB_Password(String password) {
		DB_Password = password;
	}

}
