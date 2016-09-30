package me.gking2224.refdatams.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
