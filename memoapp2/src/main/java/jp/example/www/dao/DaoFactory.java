package jp.example.www.dao;

public interface DaoFactory {

	public MemoappDao create(DaoEm em);

	public enum DaoEm {

		MySql(1), //
		MongoDB(101), //
		Redis(102), //
		HBase(103), //
		Dummy(2_147_483_647), //
		;

		final int id;

		private DaoEm(int id) {
			this.id = id;
		}
	}

}
