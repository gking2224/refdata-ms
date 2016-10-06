package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.CountryRepository;
import me.gking2224.refdatams.model.Country;

@Component
@Transactional(readOnly=true)
public class CountryDaoImpl extends AbstractDaoImpl<Country> implements CountryDao {
    
    @Autowired
    protected CountryRepository countryRepository;
    
    
    public CountryDaoImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public Country save(final Country country) {
        
        if (country.getId() == null) {
            Country existing = countryRepository.findByCode(country.getCode());
            if (existing != null) {
                country.setId(existing.getId());
            }
        }
        Country saved = countryRepository.save(country);
        flush();
        return saved;
    }

    @Override
    public Country findByCode(final String code) {
        return countryRepository.findByCode(code);
    }
}

