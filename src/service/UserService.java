package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;
import utils.CipherUtil;

public class UserService {

	public void register(User users) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(users.getPassword());
			users.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, users);

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

	public void update(User users) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(users.getPassword());
			users.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, users);

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

	private static final int LIMIT_NUM = 1000;

	public List<User> getUsers() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao UserDao = new UserDao();
			List<User> ret = UserDao.getUsers(connection, LIMIT_NUM);

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
