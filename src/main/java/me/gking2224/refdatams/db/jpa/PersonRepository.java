package me.gking2224.refdatams.db.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.refdatams.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findByFirstNameAndSurname(String firstName, String surname);

}
