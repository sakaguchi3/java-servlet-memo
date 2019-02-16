package jp.example.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.example.www.MemoBean;

public class MemoappMysqlDaoImpl implements MemoappDao {

	private DataSource getDataSource() {

		Context initContext;
		DataSource ds = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/memoapp_db");
		} catch (NamingException | ClassNotFoundException e) {
			System.out.println("exception!!!!");
			e.printStackTrace();
			throw new RuntimeException("lookup datasource failed: ", e);
		}

		return ds;
	}

	@Override
	public List<MemoBean> getMemos() {
		Connection con = null;
		Statement smt = null;
		ArrayList<MemoBean> memo_list = new ArrayList<>();

		try {
			DataSource ds = getDataSource();
			con = ds.getConnection();
			System.out.println("con: " + con);
			smt = con.createStatement();

			String select_memo = "select title, memo, modified_date from memo_data;";

			ResultSet result = smt.executeQuery(select_memo);
			while (result.next()) {
				MemoBean memoBean = new MemoBean();
				System.out.println("title: " + result.getString("title"));
				System.out.println("memo: " + result.getString("memo"));
				System.out.println("modify: " + result.getString("modified_date"));
				memoBean.setTitle(result.getString("title"));
				memoBean.setMemo(result.getString("memo"));
				memoBean.setModify(result.getString("modified_date"));
				System.out.println("memobean: " + memoBean);
				memo_list.add(memoBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memo_list;
	}

	@Override
	public void save(MemoBean memo) {

		// -- ここにDBへ保存処理 --

		DataSource ds = getDataSource();

		try (Connection con = ds.getConnection();) {

			System.out.println("title: " + memo.getTitle());
			System.out.println("text: " + memo.getMemo());

			String sql = "insert into memo_data (category, title, memo, create_date, modified_date) values (0, ?, ? , cast(now() as datetime), cast(now() as datetime));";

			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, memo.getTitle());
			prep.setString(2, memo.getMemo());

			prep.executeUpdate();

			System.out.println("sql: " + prep.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// -- ここまでDB処理 --

	}

}
