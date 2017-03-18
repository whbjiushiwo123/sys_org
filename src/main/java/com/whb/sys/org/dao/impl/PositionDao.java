package com.whb.sys.org.dao.impl;

import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.dao.IPositionDao;
import com.whb.sys.org.model.Position;

import java.util.List;

/**
 * Created by WHB on 2016/7/3.
 */
@Repository("positionDao")
public class PositionDao extends BaseDao<Position> implements IPositionDao {
    public List<Position> find() {
        return super.list("from com.whb.sys.org.model.Position");
    }

    public Position loadBySn(String sn) {
        return (Position)super.loadBySn(sn, Position.class.getName());
    }

    public List<Position> listByOrg(int orgId) {
        String hql = "select distinct p from Position p,PersonOrgPos pop where pop.posId=p.id and pop.orgId=?";
        return super.list(hql, orgId);
    }
}
