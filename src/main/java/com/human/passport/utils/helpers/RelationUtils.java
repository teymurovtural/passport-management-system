package com.human.passport.utils.helpers;

import com.human.passport.entities.Passport;
import com.human.passport.entities.Person;
import com.human.passport.exception.IdNotFoundException;
import com.human.passport.repositories.PassportRepository;
import com.human.passport.repositories.PersonRepository;

public class RelationUtils {

    public static void linkPersonToPassport(Long personId, Passport passport, PersonRepository personRepository) {
        if (personId != null) {
            Person person = personRepository.findById(personId)
                    .orElseThrow(() -> new IdNotFoundException("Person not found with id: " + personId));

            passport.setPerson(person);
            person.setPassport(passport);
        }
    }

    public static void linkPassportToPerson(Long passportId, Person person, PassportRepository passportRepository) {
        if (passportId != null) {
            Passport passport = passportRepository.findById(passportId)
                    .orElseThrow(() -> new IdNotFoundException("Passport not found with id: " + passportId));
            person.setPassport(passport);
            passport.setPerson(person);
        }
    }
}