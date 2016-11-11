package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.LocationRepository;
import me.gking2224.refdatams.model.Country;
import me.gking2224.refdatams.model.Location;

@Component
@Transactional(readOnly=true)
public class LocationDaoImpl extends AbstractDaoImpl<Location, Long> implements LocationDao {

    @Autowired
    protected LocationRepository repository;
    
    
    public LocationDaoImpl() {
    }

    @Override
    public Location findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    protected JpaRepository<Location, Long> getRepository() {
        return repository;
    }


    @Override
    protected Location findExisting(Location location) {
        return findByName(location.getName());
    }
}
