package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.BuildingRepository;
import me.gking2224.refdatams.model.Building;

@Component
@Transactional(readOnly=true)
public class BuildingDaoImpl extends AbstractDaoImpl<Building> implements BuildingDao {
    
    @Autowired
    protected BuildingRepository repository;
    
    
    public BuildingDaoImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public Building save(final Building building) {
        
        if (building.getId() == null) {
            Building existing = repository.findByNameAndCityName(building.getName(), building.getCity().getName());
            if (existing != null) {
                building.setId(existing.getId());
            }
        }
        Building saved = repository.save(building);
        flush();
        return saved;
    }

    @Override
    public Building findByName(final String name) {
        return repository.findByName(name);
    }
}

