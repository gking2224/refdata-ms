package me.gking2224.refdatams.db.dao;

import java.util.List;

import me.gking2224.refdatams.model.Person;

public interface PersonDao {

    List<Person> findByFirstNameAndSurname(String firstName, String surname);

    Person save(Person person);

}
