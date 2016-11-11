package me.gking2224.refdatams.db.jpa;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

    Resource findByBillRateAndPerson_FirstNameAndPerson_SurnameAndLocation_NameAndContractType_Code(
            BigDecimal billRate, String firstName, String surname, String locationName, String contractTypeCode);
}
//AndLocationNameAndContractTypeCode
//
