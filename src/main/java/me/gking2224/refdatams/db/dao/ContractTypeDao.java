package me.gking2224.refdatams.db.dao;

import me.gking2224.refdatams.model.ContractType;

public interface ContractTypeDao {

    ContractType findByCode(String code);
}
