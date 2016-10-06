package me.gking2224.refdatams.db.dao;

import me.gking2224.refdatams.model.Building;

public interface BuildingDao {

    Building save(Building building);

    Building findByName(String name);

}
