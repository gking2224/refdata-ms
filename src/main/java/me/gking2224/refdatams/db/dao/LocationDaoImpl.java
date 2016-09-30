package me.gking2224.refdatams.db.dao;

import java.util.List;

import org.hibernate.Hibernate;
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
    protected LocationRepository locationRepository;
    
    
    public LocationDaoImpl() {
    }

    @Override
    public List<Location> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        locations.forEach(l -> {
            populateLocation(l);
        });
        return locations;
    }

    private void populateLocation(Location l) {

        Hibernate.initialize(l.getBuilding());
        Hibernate.initialize(l.getCity());
        Hibernate.initialize(l.getCountry());
        if (l.getBuilding() != null) {
            Hibernate.initialize(l.getBuilding().getCity());
            l.setCity(l.getBuilding().getCity());
        }
        if (l.getCity() != null) {
            Hibernate.initialize(l.getCity().getCountry());
            l.setCountry(l.getCity().getCountry());
        }
        getEntityManager().detach(l);
        if (l.getBuilding() != null) {
            l.getBuilding().setCity(null);
        }
        if (l.getCity() != null) {
            l.getCity().setCountry(null);
        }
    }
}
