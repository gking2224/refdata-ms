package me.gking2224.refdatams.db.dao;

import me.gking2224.refdatams.model.City;

public interface CityDao {

    City save(City c);

    City findByName(String name);

}
