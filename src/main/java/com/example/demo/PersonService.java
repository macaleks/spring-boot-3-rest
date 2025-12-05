package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    public Person logPerson(Person person) {
        log.info("Received person: name={}, age={}", person.name(), person.age());
        return person;
    }
}
