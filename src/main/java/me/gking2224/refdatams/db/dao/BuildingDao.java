package me.gking2224.refdatams.db.dao;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.Building;

public interface BuildingDao extends CrudDao<Building, Long> {

    Building findByName(String name);

}
