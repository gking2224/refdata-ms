package me.gking2224.refdatams.db.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.ResourceRepository;
import me.gking2224.refdatams.model.Resource;

@Component
@Transactional
public class ResourceDaoImpl extends AbstractDaoImpl<Resource, Long> implements ResourceDao {

    @Autowired
    protected ResourceRepository repository;
    
    
    public ResourceDaoImpl() {
    }

    @Override
    protected JpaRepository<Resource, Long> getRepository() {
        return repository;
    }

    protected Resource findExisting(final Resource r) {
        BigDecimal billRate = r.getBillRate();
        String locationName = r.getLocationName();
        String firstName = r.getFirstName();
        String surname = r.getSurname();
        String contractTypeCode = r.getContractTypeCode();
        
        return repository.findByBillRateAndPerson_FirstNameAndPerson_SurnameAndLocation_NameAndContractType_Code(
                billRate, firstName, surname, locationName, contractTypeCode);
        
    }
}
