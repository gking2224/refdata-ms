package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

    City findByNameAndCountryName(String city, String country);
}
