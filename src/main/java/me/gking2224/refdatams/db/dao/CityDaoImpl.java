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
    public City findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    protected JpaRepository<City, Long> getRepository() {
        return repository;
    }

    @Override
    public City findExisting(City city) {
        return findByName(city.getName());
    }
}

