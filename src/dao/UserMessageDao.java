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

	public List<UserMessage> getUserMessages(Connection connection,String startDate,String endDate) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM messageslist WHERE ? <= created_at AND ? >= created_at");
			sql.append(" ORDER BY created_at DESC");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, startDate);
			ps.setString(2, endDate);

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
				message.setUserId(user_id);
				message.setBranchId(branch_id);
				message.setPositionId(position_id);
				message.setText(text);
				message.setCategory(category);
				message.setName(name);
				message.setTitle(title);
				message.setCreatedAt(created_at);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
