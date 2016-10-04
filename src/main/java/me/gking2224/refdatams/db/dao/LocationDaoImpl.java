package me.gking2224.refdatams.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.LocationRepository;
import me.gking2224.refdatams.model.Location;

@Component
@Transactional
public class LocationDaoImpl extends AbstractDaoImpl<Location> implements LocationDao {

    @Autowired
    protected LocationRepository repository;
    
    
    public LocationDaoImpl() {
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = repository.findAll();
        return locations;
    }

    @Override
    public Location findByName(String name) {
        return repository.findByName(name);
    }
}
