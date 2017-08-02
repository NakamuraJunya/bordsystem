package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import dao.BranchDao;

public class BranchService {

	public void register(Branch branch) {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			branchDao.insert(connection, branch);

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

	public List<Branch> getBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getBranch(connection, LIMIT_NUM);

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

