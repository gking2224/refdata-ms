package me.gking2224.refdatams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.db.dao.LocationDao;
import me.gking2224.refdatams.model.Location;

@Component
@Transactional(readOnly=true)
public class RefDataServiceImpl implements RefDataService {


    @Autowired
    private LocationDao dao;

    public RefDataServiceImpl() {
    }

    @Override
    public List<Location> findAllLocations() {
        return dao.findAllLocations();
    }

}
