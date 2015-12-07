package com.example;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RepositoryEventHandler
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonRepositoryEventHandler {

    private final PersonRepository personRepository;
    private final EntityManager entityManager;


    @HandleBeforeSave
    public void compareName(Person newPerson) {
        log.info("start HandleBeforeSave");
        entityManager.detach(newPerson);

        Person oldPerson = personRepository.findOne(newPerson.getId());

        log.info("name for person {} - old name '{}' - new name '{}'", newPerson.getId(),
                oldPerson.getName(), newPerson.getName());

        if (oldPerson.getName().equals(newPerson.getName())) {
            throw new IllegalArgumentException("names not different in HandleBeforeSave");
        }
    }

    @HandleAfterSave
    public void logAfterSave(Person person) {
        log.info("start HandleAfterSave");
    }
}
