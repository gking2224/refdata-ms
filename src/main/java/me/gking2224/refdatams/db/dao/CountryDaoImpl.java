package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.CountryRepository;
import me.gking2224.refdatams.model.Country;

@Component
@Transactional()
public class CountryDaoImpl extends AbstractDaoImpl<Country> implements CountryDao {
    
    @Autowired
    protected CountryRepository countryRepository;
    
    
    public CountryDaoImpl() {
    }

    @Override
    public Country save(Country country) {
        
        if (country.getId() == null) {
            Country existing = countryRepository.findByCode(country.getCode());
            if (existing != null) {
                country.setId(existing.getId());
            }
        }
        return countryRepository.save(country);
    }
}

