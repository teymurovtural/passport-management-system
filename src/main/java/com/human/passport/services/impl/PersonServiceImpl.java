package com.human.passport.services.impl;

import com.human.passport.dto.request.PersonRequestDto;
import com.human.passport.dto.response.PersonResponseDto;
import com.human.passport.entities.Passport;
import com.human.passport.entities.Person;
import com.human.passport.exception.IdNotFoundException;
import com.human.passport.mapper.PersonMapper;
import com.human.passport.repositories.PassportRepository;
import com.human.passport.repositories.PersonRepository;
import com.human.passport.services.PersonService;
import com.human.passport.specification.PersonSpecification;
import com.human.passport.utils.enums.Gender;
import com.human.passport.utils.helpers.RelationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.human.passport.specification.PersonSpecification.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PassportRepository passportRepository;

    @Override
    @Transactional
    public PersonResponseDto addNewPerson(PersonRequestDto dto) {
        Person person = personMapper.dtoToEntity(dto);
        RelationUtils.linkPassportToPerson(dto.getPassportId(), person, passportRepository);
        return personMapper.entityToDto(personRepository.save(person));
    }

    @Override
    @Transactional
    public PersonResponseDto updatePerson(Long id, PersonRequestDto dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Person not found with id: " + id));

        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setBirthDate(dto.getBirthDate());
        person.setGender(dto.getGender());
        person.setEmail(dto.getEmail());
        person.setPhoneNumber(dto.getPhoneNumber());

        RelationUtils.linkPassportToPerson(dto.getPassportId(), person, passportRepository);

        return personMapper.entityToDto(personRepository.save(person));
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Person not found with id: " + id));
        personRepository.delete(person);
    }

    @Override
    public PersonResponseDto getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Person not found with id: " + id));
        return personMapper.entityToDto(person);
    }

    @Override
    public List<PersonResponseDto> getAllPersons() {
        return personRepository.findAll().stream()
                .map(personMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonResponseDto> searchPersons(String firstName, String lastName, Gender gender) {
        return personRepository.findAll(
                        Specification.where(hasFirstName(firstName))
                                .and(hasLastName(lastName))
                                .and(hasGender(gender))
                )
                .stream()
                .map(personMapper::entityToDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteSoftPerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Person not found with id: " + id));
        person.setActive(false);
        personRepository.save(person);
    }

    @Override
    public Page<PersonResponseDto> searchPersons(
            String firstName,
            String lastName,
            Gender gender,
            LocalDate birthStart,
            LocalDate birthEnd,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Person> personsPage = personRepository.findAll(
                Specification.where(PersonSpecification.hasFirstName(firstName))
                        .and(PersonSpecification.hasLastName(lastName))
                        .and(PersonSpecification.hasGender(gender))
                        .and(PersonSpecification.birthDateBetween(birthStart, birthEnd)),
                pageRequest
        );

        return personsPage.map(personMapper::entityToDto);
    }

}
