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

public class UserDao {

	public User getUser(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ? AND is_working = 1";

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
				int id= rs.getInt("id");
				String login_id = rs.getString("login_id");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				int is_working = rs.getInt("is_working");
				String name = rs.getString("name");
				String password = rs.getString("password");

				User user = new User();
				user.setId(id);
				user.setLoginId(login_id);
				user.setBranchId(branch_id);
				user.setPositionId(position_id);
				user.setIs_working(is_working);
				user.setName(name);
				user.setPassword(password);

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

			ps.setString(1, users.getLoginId());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setInt(4, users.getBranchId());
			ps.setInt(5, users.getPositionId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
		public void update(Connection connection, User users) {
			System.out.println("bbb");

			PreparedStatement ps = null;
			try {System.out.println("aaa");
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET");
				sql.append("  login_id = ?");
				if(!users.getPassword().isEmpty()) {
				sql.append(", password = ?");
				}
				sql.append(", name = ?");
				sql.append(", branch_id = ?");
				sql.append(", position_id = ?");
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, users.getLoginId());
				if(!users.getPassword().isEmpty()) {
				ps.setString(2, users.getPassword());
				ps.setString(3, users.getName());
				ps.setInt(4, users.getBranchId());
				ps.setInt(5, users.getPositionId());
				ps.setInt(6, users.getId());
				}else{
					ps.setString(2, users.getName());
					ps.setInt(3, users.getBranchId());
					ps.setInt(4, users.getPositionId());
					ps.setInt(5, users.getId());
				}

				System.out.println(ps);

				ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}
		public void is_working(Connection connection, int id,int is_working) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET");
				sql.append(" is_working = ?");
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setInt(1,is_working);
				ps.setInt(2,id);

				System.out.println(ps.toString());

				ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

	public List<User> getUsers(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");
			sql.append(" ORDER BY branch_id,position_id");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public User getUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public User getUser(Connection connection, String login_id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}