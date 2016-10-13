package me.gking2224.refdatams.db.dao;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.Country;

public interface CountryDao extends CrudDao<Country, Long> {

    Country findByCode(String code);
}
