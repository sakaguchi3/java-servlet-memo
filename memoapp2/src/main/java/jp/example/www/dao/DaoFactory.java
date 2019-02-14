package jp.example.www.dao;

public interface DaoFactory {

	public MemoappDao create(DaoEm em);

	public enum DaoEm {

		MySsql(1), //
		MongoDB(2), //
		Other(2_147_483_647), //
		;

		final int id;

		private DaoEm(int id) {
			this.id = id;
		}
	}

}
