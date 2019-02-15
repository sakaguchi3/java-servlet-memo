package jp.example.www.dao;

public class DaoFactoryImpl implements DaoFactory {

	@Override
	public MemoappDao create(DaoEm em) {

		if (em.equals(DaoEm.MySql)) {
			return new MemoappMysqlDaoImpl();
		}

		if (em.equals(DaoEm.Dummy)) {
			return new DummyDaoImpl();
		}

		throw new IllegalArgumentException();
	}

}
