package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Message;
import beans.User;
import beans.UserMessage;
import dao.MessageDao;
import dao.UserDao;
import dao.UserMessageDao;

public class MessageService {

	public void register(Message messages) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, messages);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public void delete(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.delete(connection, id);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public User getUser(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao UserDao = new UserDao();
			User user = UserDao
					.getUser(connection, id);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<UserMessage> getMessage(String startDate ,String endDate,String category) {

		Connection connection = null;
		try {

			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection,startDate,endDate,category);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public List<UserMessage> getMessageCategory() {

		Connection connection = null;
		try {

			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessageCategory(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
