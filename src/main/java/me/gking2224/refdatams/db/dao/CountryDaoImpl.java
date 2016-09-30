package me.gking2224.refdatams.db.dao;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

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
    @Transactional(propagation=REQUIRES_NEW)
    public void batchSaveOrUpdate(Country country) {
        
        if (country.getId() == null) {
            Country existing = countryRepository.findByCode(country.getCode());
            if (existing != null) {
                country.setId(existing.getId());
            }
        }
        countryRepository.save(country);
    }
}

