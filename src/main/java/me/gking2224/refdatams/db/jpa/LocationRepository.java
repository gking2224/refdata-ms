package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

    Location findByName(String name);

}
