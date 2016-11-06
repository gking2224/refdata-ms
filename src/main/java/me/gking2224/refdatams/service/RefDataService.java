package me.gking2224.refdatams.service;

import java.util.List;

import me.gking2224.refdatams.model.Building;
import me.gking2224.refdatams.model.City;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Country;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Resource;

public interface RefDataService {

    List<Location> findAllLocations();
    
    List<Resource> findAllResources();

    Resource saveResource(Resource r);

    Location saveLocation(Location l);

    Country saveCountry(Country c);

    City saveCity(City c);

    Building saveBuilding(Building b);

    Location updateLocationWithRates(Location l);

    List<ContractType> findAllContractTypes();
}
