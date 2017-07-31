package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;

public class UserDao<UserMessage> {

	public User getUser(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				String password = rs.getString("password");
				int position_id = rs.getInt("position_id");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setPassword(password);
				user.setPosition_id(position_id);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", is_working");
			sql.append(") VALUES (");
			sql.append("?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", 1"); //is_working
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, users.getLogin_id());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setInt(4, users.getBranch_id());
			ps.setInt(5, users.getPosition_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}