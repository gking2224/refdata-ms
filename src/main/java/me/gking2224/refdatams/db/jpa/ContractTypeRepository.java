package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.ContractType;

public interface ContractTypeRepository extends JpaRepository<ContractType, Long>{

    ContractType findByCode(String code);
}
