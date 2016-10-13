package me.gking2224.refdatams.db.dao;

import java.util.List;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.refdatams.model.Person;

public interface PersonDao extends CrudDao<Person, Long>{

    List<Person> findByFirstNameAndSurname(String firstName, String surname);

}
