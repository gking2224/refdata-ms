package me.gking2224.refdatams.db.dao;

import me.gking2224.refdatams.model.Country;

public interface CountryDao {
    
    Country save(Country country);

    Country findByCode(String code);
}
