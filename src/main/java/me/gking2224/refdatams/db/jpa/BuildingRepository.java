package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long>{

    Building findByName(String name);

    Building findByNameAndCityName(String building, String city);
}
