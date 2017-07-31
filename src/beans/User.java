package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String name;
	private int branch_id;
	private String password;
	private int position_id;
	private Date is_working;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public Date getIs_working() {
		return is_working;
	}

	public void setIs_working(Date is_working) {
		this.is_working = is_working;
	}

	public void setBranch_id(String parameter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setPosition_id(String parameter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public static int getUser_id() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
