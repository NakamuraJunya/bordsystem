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

	public List<UserComment> getUserComments(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM commentslist ");
			sql.append(" ORDER BY created_at DESC");

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
				comments.setUserId(user_id);
				comments.setBranchId(branch_id);
				comments.setPositionId(position_id);
				comments.setMessageId(message_id);
				comments.setText(text);
				comments.setCreatedAt(created_at);

				ret.add(comments);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
