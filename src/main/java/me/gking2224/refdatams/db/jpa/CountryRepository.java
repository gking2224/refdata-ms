package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

    Country findByCode(String code);
}
