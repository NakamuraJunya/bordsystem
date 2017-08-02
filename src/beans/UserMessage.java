package beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private int branch_id;
	private int position_id;
	private String text;
	private String title;
	private String category;
	private String name;
	private Date created_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
			return name;
	}

	public void setName(String name) {
			this.name = name;
    }
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
