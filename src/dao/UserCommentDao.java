package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserComment;
import exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM commentslist ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int branch_id = rs.getInt("branch_id");
				int position_id = rs.getInt("position_id");
				int message_id = rs.getInt("message_id");
				String text = rs.getString("text");
				Timestamp created_at = rs.getTimestamp("created_at");

				UserComment comments = new UserComment();
				comments.setId(id);
				comments.setUser_id(user_id);
				comments.setBranch_id(branch_id);
				comments.setPosition_id(position_id);
				comments.setMessage_id(message_id);
				comments.setText(text);
				comments.setCreated_at(created_at);

				ret.add(comments);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
