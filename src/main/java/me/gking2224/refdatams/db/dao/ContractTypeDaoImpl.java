package me.gking2224.refdatams.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.ContractTypeRepository;
import me.gking2224.refdatams.model.ContractType;

@Component
@Transactional
public class ContractTypeDaoImpl extends AbstractDaoImpl<ContractType, Long> implements ContractTypeDao {

    @Autowired
    protected ContractTypeRepository repository;
    
    
    public ContractTypeDaoImpl() {
    }

    @Override
    public ContractType findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    protected JpaRepository<ContractType, Long> getRepository() {
        return repository;
    }
}
