package me.gking2224.refdatams.db.dao;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.Location;

public interface LocationDao extends CrudDao<Location, Long> {

    Location findByName(String name);

}
