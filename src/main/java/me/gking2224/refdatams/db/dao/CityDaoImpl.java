package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.CityRepository;
import me.gking2224.refdatams.model.City;

@Component
@Transactional(readOnly=true)
public class CityDaoImpl extends AbstractDaoImpl<City, Long> implements CityDao {
    
    @Autowired
    protected CityRepository repository;
    
    public CityDaoImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public City save(City city) {
        
        if (city.getId() == null) {
            City existing = repository.findByNameAndCountryName(city.getName(), city.getCountry().getName());
            if (existing != null) {
                city.setId(existing.getId());
            }
        }
        City saved = super.save(city);
        flush();
        return saved;
    }

    @Override
    public City findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    protected JpaRepository<City, Long> getRepository() {
        return repository;
    }
}

