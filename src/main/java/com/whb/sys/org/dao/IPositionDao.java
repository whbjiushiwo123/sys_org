package com.whb.sys.org.dao;


import java.util.List;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.model.Position;

/**
 * Created by WHB on 2016/7/2.
 */
public interface IPositionDao extends IBaseDao<Position> {
    public List<Position> find();
    public Position loadBySn(String sn);
    /**
     * 获取某个组织中存在的所有岗位列表
     * @param orgId
     * @return
     */
    public List<Position> listByOrg(int orgId);

}
