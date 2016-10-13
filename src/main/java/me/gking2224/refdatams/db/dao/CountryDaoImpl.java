package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.CountryRepository;
import me.gking2224.refdatams.model.Country;

@Component
@Transactional(readOnly=true)
public class CountryDaoImpl extends AbstractDaoImpl<Country, Long> implements CountryDao {
    
    @Autowired
    protected CountryRepository repository;
    
    
    public CountryDaoImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public Country save(final Country country) {
        
        if (country.getId() == null) {
            Country existing = repository.findByCode(country.getCode());
            if (existing != null) {
                country.setId(existing.getId());
            }
        }
        Country saved = repository.save(country);
        flush();
        return saved;
    }

    @Override
    public Country findByCode(final String code) {
        return repository.findByCode(code);
    }

    @Override
    protected JpaRepository<Country, Long> getRepository() {
        return repository;
    }
}

