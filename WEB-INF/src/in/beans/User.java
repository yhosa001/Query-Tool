package in.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user1")
public class User {

	@Id
	@Column(name = "userid")
	private String userid;

	@Column(name = "password", length = 70)
	private String password;

	@Column(name = "NumbeOfHistory")
	private String NumbeOfHistory;

	/**
	 * @return the numbeOfHistory
	 */
	public String getNumbeOfHistory() {
		return NumbeOfHistory;
	}

	/**
	 * @param numbeOfHistory the numbeOfHistory to set
	 */
	public void setNumbeOfHistory(String numbeOfHistory) {
		this.NumbeOfHistory = numbeOfHistory;
	}

	public void setuserid(String userid) {
		this.userid = userid;
	}

	public String getuserid() {
		return userid;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getpassword() {
		return password;
	}

}