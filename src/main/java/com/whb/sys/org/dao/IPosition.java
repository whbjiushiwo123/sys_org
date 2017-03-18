package com.whb.sys.org.dao;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.model.Position;

public interface IPosition extends IBaseDao<Position> {
	public Pager<Position> find();
}
