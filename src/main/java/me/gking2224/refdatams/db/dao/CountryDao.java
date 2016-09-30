package me.gking2224.refdatams.db.dao;

import me.gking2224.refdatams.model.Country;

public interface CountryDao {
    
    void batchSaveOrUpdate(Country country);

}
