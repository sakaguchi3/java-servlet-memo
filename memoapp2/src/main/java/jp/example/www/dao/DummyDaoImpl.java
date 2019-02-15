package jp.example.www.dao;

import java.util.ArrayList;
import java.util.List;

import jp.example.www.MemoBean;

public class DummyDaoImpl implements MemoappDao{

	@Override
	public List<MemoBean> getMemos() {
		List<MemoBean> ret = new ArrayList<>();
		return ret;
	}

	@Override
	public void save(MemoBean memo) {

	}

}
