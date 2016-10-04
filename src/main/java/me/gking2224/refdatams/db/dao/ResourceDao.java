package me.gking2224.refdatams.db.dao;

import java.util.List;

import me.gking2224.refdatams.model.Resource;

public interface ResourceDao {

    List<Resource> findAll();

    Resource batchSaveOrUpdate(Resource c);

    Resource save(Resource r);

}
