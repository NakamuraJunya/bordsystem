package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comments) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" user_id");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", message_id");
			sql.append(", text");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append("?"); // user_id
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", ?"); // message_id
			sql.append(", ?"); // text
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comments.getUser_id());
			ps.setInt(2, comments.getBranch_id());
			ps.setInt(3, comments.getPosition_id());
			ps.setInt(4, comments.getMessage_id());
			ps.setString(5, comments.getText());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
