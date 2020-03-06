package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    //Create Person with an id
    int insertPerson(UUID id, Person person);

    //Create person with random id
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    //Return list of persons
    List<Person> getAllPersons();

    //Return Person
    Optional<Person> getPerson(UUID id);

    //Delete Person using id
    int deletePerson(UUID id);

    //Update person using id
    int updatePerson(UUID id, Person person);


}
