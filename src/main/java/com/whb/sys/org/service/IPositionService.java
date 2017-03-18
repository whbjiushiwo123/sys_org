package com.whb.sys.org.service;

import com.whb.sys.org.model.Position;

import java.util.List;

/**
 * Created by WHB on 2016/7/7.
 */
public interface IPositionService {
    public void add(Position pos);

    public void update(Position pos);

    public void delete(int id);

    public Position load(int id);

    public Position get(int id);
    public List<Position> find();

    public List<Position> listByOrg(int orgId);
}
