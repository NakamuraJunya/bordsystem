package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserMessage;
import exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM messageslist ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				String text = rs.getString("text");
				String title = rs.getString("title");
				String name = rs.getString("name");
				String category = rs.getString("category");
				Timestamp created_at = rs.getTimestamp("created_at");

				UserMessage message = new UserMessage();
				message.setId(id);
				message.setUser_id(user_id);
				message.setBranch_id(branch_id);
				message.setPosition_id(position_id);
				message.setText(text);
				message.setCategory(category);
				message.setName(name);
				message.setTitle(title);
				message.setCreated_at(created_at);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
