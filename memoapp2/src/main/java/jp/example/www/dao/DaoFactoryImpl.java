package jp.example.www.dao;

public class DaoFactoryImpl implements DaoFactory {

	@Override
	public MemoappDao create(DaoEm em) {

		if (em.equals(DaoEm.MySsql)) {
			return new MemoappMysqlDaoImpl();
		}

		throw new IllegalArgumentException();
	}


}
