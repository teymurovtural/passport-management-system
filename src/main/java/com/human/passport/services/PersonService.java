package com.human.passport.services;

import com.human.passport.dto.request.PersonRequestDto;
import com.human.passport.dto.response.PersonResponseDto;
import com.human.passport.utils.enums.Gender;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {

    PersonResponseDto addNewPerson(PersonRequestDto personRequestDto);
    PersonResponseDto updatePerson(Long id, PersonRequestDto dto);
    void deletePerson(Long id);
    PersonResponseDto getPersonById(Long id);
    List<PersonResponseDto> getAllPersons();
    List<PersonResponseDto> searchPersons(String firstName, String lastName, Gender gender);
    void deleteSoftPerson(Long id);

    Page<PersonResponseDto> searchPersons(
            String firstName,
            String lastName,
            Gender gender,
            LocalDate birthStart,
            LocalDate birthEnd,
            int page,
            int size,
            String sortBy,
            String direction
    );


}
