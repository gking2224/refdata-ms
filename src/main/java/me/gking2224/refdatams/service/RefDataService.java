package me.gking2224.refdatams.service;

import java.util.List;

import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Resource;

public interface RefDataService {

    List<Location> findAllLocations();
    
    List<Resource> findAllResources();
}
