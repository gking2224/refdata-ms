package me.gking2224.refdatams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.db.dao.LocationDao;
import me.gking2224.refdatams.db.dao.ResourceDao;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Resource;

@Component
@Transactional(readOnly=true)
public class RefDataServiceImpl implements RefDataService {


    @Autowired
    private LocationDao locationDao;
    
    @Autowired
    private ResourceDao resourceDao;

    public RefDataServiceImpl() {
    }

    @Override
    public List<Location> findAllLocations() {
        return locationDao.findAll();
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceDao.findAll();
    }

}
