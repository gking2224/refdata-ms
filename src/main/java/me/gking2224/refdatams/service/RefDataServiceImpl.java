package me.gking2224.refdatams.service;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.refdatams.db.dao.ContractTypeDao;
import me.gking2224.refdatams.db.dao.LocationDao;
import me.gking2224.refdatams.db.dao.PersonDao;
import me.gking2224.refdatams.db.dao.ResourceDao;
import me.gking2224.refdatams.model.ContractType;
import me.gking2224.refdatams.model.Location;
import me.gking2224.refdatams.model.Person;
import me.gking2224.refdatams.model.Resource;

@Component
@Transactional(readOnly=true)
public class RefDataServiceImpl implements RefDataService {


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
        return locationDao.findAll();
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceDao.findAll();
    }

    @Override
    @Transactional(readOnly=false, propagation=REQUIRES_NEW)
    public Resource batchSaveResource(final Resource r) {
        return _saveResource(r,  (res) -> resourceDao.batchSaveOrUpdate(res));
    }

    @Override
    @Transactional(readOnly=false)
    public Resource saveResource(Resource r) {
        return _saveResource(r,  (res) -> resourceDao.save(res));
    }

    protected Resource _saveResource(Resource r, Function<Resource, Resource> callDaoFunction) {
        
        Location l = locationDao.findByName(r.getLocationName());
        
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
                ;
        return callDaoFunction.apply(r);
    }

}
