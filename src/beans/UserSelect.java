package beans;

import java.io.Serializable;

public class UserSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String name;
	private int branchId;
	private String password;
	private int positionId;
	private int isWorking;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(int isWorking) {
		this.isWorking = isWorking;
	}
}