package me.gking2224.refdatams.service;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.db.dao.BuildingDao;
import me.gking2224.refdatams.db.dao.CityDao;
import me.gking2224.refdatams.db.dao.ContractTypeDao;
import me.gking2224.refdatams.db.dao.CountryDao;
import me.gking2224.refdatams.db.dao.LocationDao;
import me.gking2224.refdatams.db.dao.PersonDao;
import me.gking2224.refdatams.db.dao.ResourceDao;
import me.gking2224.refdatams.model.Building;
import me.gking2224.refdatams.model.City;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Country;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.LocationRate;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;

@Component
@Transactional(readOnly=true)
public class RefDataServiceImpl implements RefDataService {


    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private CountryDao countryDao;
    
    @Autowired
    private LocationDao locationDao;
    
    @Autowired
    private ResourceDao resourceDao;
    
    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private ContractTypeDao contractTypeDao;

    public RefDataServiceImpl() {
    }

    @Override
    public List<Location> findAllLocations() {
        return locationDao.findAll().stream().map(this::populateLocation).collect(Collectors.toList());
    }

    private Location populateLocation(Location l) {
        if (l.getBuilding() != null) {
            l.setCity(l.getBuilding().getCity());
        }
        if (l.getCity() != null) {
            l.setCountry(l.getCity().getCountry());
        }
        return l;
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceDao.findAll();
    }

    @Override
    @Transactional(readOnly=false)
    public Resource saveResource(Resource r) {
        
        String locationName = r.getLocationName();
        Location l = null;
        if (locationName != null) {
            l = locationDao.findByName(locationName);
            if (l == null)
                throw new DataIntegrityViolationException(format("Could not find location %s", locationName));
        }
        
        
        ContractType ct = contractTypeDao.findByCode(r.getContractTypeCode());
        List<Person> pp = personDao.findByFirstNameAndSurname(r.getFirstName(), r.getSurname());
        
        Person p = null;
        if (pp.size() > 1) {
            throw new RuntimeException(format("non-unique person identifier %s %s}", r.getFirstName(), r.getSurname()));
        }
        else if (pp.size() == 1) {
            p = pp.get(0);
        }
        else if (pp.size() == 0) {
            p = personDao.save(r.getPerson());
        }
        
        r.setContractType(ct);
        r.setLocation(l);
        r.setPerson(p);
        return resourceDao.save(r);
    }

    @Override
    @Transactional(readOnly=false)
    public Location saveLocation(final Location l) {
        
        if (l.getBuilding() != null) {
            l.setBuilding(saveBuilding(l.getBuilding()));
            l.setCity(null);
            l.setCountry(null);
        }
        else if (l.getCity() != null) {
            l.setCity(saveCity(l.getCity()));
            l.setCountry(null);
        }
        else if (l.getCountry() != null) {
            l.setCountry(countryDao.findByCode(l.getCountry().getCode()));
        }
        
        return locationDao.save(l);
        
    }

    @Override
    @Transactional(readOnly=false)
    public Building saveBuilding(final Building b) {
        
        if (b.getCity() != null) {
            b.setCity(saveCity(b.getCity()));
        }
        
        return buildingDao.save(b);
        
    }

    @Override
    @Transactional(readOnly=false)
    public City saveCity(final City c) {
        if (c.getCountry() != null) {
            c.setCountry(countryDao.findByCode(c.getCountry().getCode()));
        }
        
        return cityDao.save(c);
        
    }

    @Override
    @Transactional(readOnly=false)
    public Country saveCountry(final Country c) {
        return countryDao.save(c);
        
    }

    @Override
    @Transactional(readOnly=false)
    public Location updateLocationWithRates(Location l) {
        Location existing = locationDao.findByName(l.getName());
        if (existing == null) throw new DataIntegrityViolationException(format("Location %s not found", l.getName()));
        l.getLocationRates().forEach(r -> {
            Optional<LocationRate> existingRate = existing.getRate(r.getContractType());
            if (existingRate.isPresent()) existingRate.get().setRate(r.getRate());
            else {
                ContractType ct = contractTypeDao.findByCode(r.getContractTypeCode());
                if (ct == null)
                    throw new DataIntegrityViolationException(
                            format("Contract Type %s not found", r.getContractTypeCode()));
                LocationRate newRate = new LocationRate();
                newRate.setLocation(existing);
                newRate.setContractType(ct);
                newRate.setRate(r.getRate());
                existing.addLocationRate(newRate);
            }
        });
        return locationDao.save(existing);
    }

}
