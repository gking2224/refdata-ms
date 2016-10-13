package me.gking2224.refdatams.db.dao;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.City;

public interface CityDao extends CrudDao<City, Long> {

    City findByName(String name);

}
