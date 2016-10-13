package me.gking2224.refdatams.db.dao;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.ContractType;

public interface ContractTypeDao extends CrudDao<ContractType, Long>{

    ContractType findByCode(String code);
}
