package com.whb.sys.org.service.impl;

import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.whb.sys.org.dao.IPositionDao;
import com.whb.sys.org.model.Position;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IPositionService;

@Service("positionService")
public class PositionServiceImpl extends AbstractBasicService implements IPositionService {
	@Inject
	private IPositionDao positionDao;

	public void add(Position pos) {
		if(positionDao.loadBySn(pos.getSn())!=null) throw new SysException("添加的岗位的sn已经存在");
		positionDao.add(pos);
	}

	public void update(Position pos) {
		//if(positionDao.loadBySn(pos.getSn())!=null) throw new SysException("添加的岗位的sn已经存在");
		positionDao.update(pos);
	}

	public void delete(int id) {
		positionDao.delete(id);
	}

	public Position load(int id) {
		return positionDao.load(id);
	}
	public Position get(int id) {
		return positionDao.get(id);
	}
	public List<Position> find() {
		return positionDao.find();
	}

	public List<Position> listByOrg(int orgId) {
		return positionDao.listByOrg(orgId);
	}
}
