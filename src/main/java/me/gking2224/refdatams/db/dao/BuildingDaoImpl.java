package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.BuildingRepository;
import me.gking2224.refdatams.model.Building;

@Component
@Transactional(readOnly=true)
public class BuildingDaoImpl extends AbstractDaoImpl<Building, Long> implements BuildingDao {
    
    @Autowired
    protected BuildingRepository repository;
    
    public BuildingDaoImpl() {
    }

    @Override
    public Building findByName(final String name) {
        return repository.findByName(name);
    }

    @Override
    protected JpaRepository<Building, Long> getRepository() {
        return repository;
    }

    @Override
    protected Building findExisting(Building building) {
        return findByName(building.getName());
    }
}

