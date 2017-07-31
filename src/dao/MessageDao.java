package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Message;
import exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message messages) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append(" user_id");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append(" ?"); // user_id
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // position_id
			sql.append(", ?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, messages.getUser_id());
			ps.setInt(2, messages.getBranch_id());
			ps.setInt(3, messages.getPosition_id());
			ps.setString(4, messages.getTitle());
			ps.setString(5, messages.getText());
			ps.setString(6, messages.getCategory());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
