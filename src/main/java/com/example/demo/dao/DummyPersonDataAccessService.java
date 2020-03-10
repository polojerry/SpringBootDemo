package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dummyDao")
public class DummyPersonDataAccessService implements PersonDao {

    private List<Person> db = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(id, person.getName()));
        return 0;
    }

    @Override
    public List<Person> getAllPersons() {
        return db;
    }

    @Override
    public Optional<Person>  getPerson(UUID id) {
        return db.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> person = getPerson(id);
        if(person.isEmpty()){
            return 0;
        }
        db.remove(person.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person personUpdate) {
        return getPerson(id)
                .map(person -> {
                    int personIndex = db.indexOf(person);
                    if(personIndex >=0){
                        db.set(personIndex, new Person(id, personUpdate.getName()));
                        return 1;
                    }else{
                        return 0;
                    }
                }).orElse(0);
    }
}
