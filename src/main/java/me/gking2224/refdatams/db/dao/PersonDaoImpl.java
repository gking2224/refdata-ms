package me.gking2224.refdatams.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.PersonRepository;
import me.gking2224.refdatams.model.Person;

@Component
@Transactional
public class PersonDaoImpl extends AbstractDaoImpl<Person, Long> implements PersonDao {

    @Autowired
    protected PersonRepository repository;
    
    
    public PersonDaoImpl() {
    }

    @Override
    public List<Person> findByFirstNameAndSurname(final String firstName, final String surname) {
        return repository.findByFirstNameAndSurname(firstName, surname);
    }

    @Override
    protected JpaRepository<Person, Long> getRepository() {
        return repository;
    }
}
