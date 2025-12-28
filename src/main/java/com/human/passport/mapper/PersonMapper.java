package com.human.passport.mapper;

import com.human.passport.dto.request.PersonRequestDto;
import com.human.passport.dto.response.PersonResponseDto;
import com.human.passport.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final PassportMapper passportMapper;

    public PersonMapper(PassportMapper passportMapper) {
        this.passportMapper = passportMapper;
    }

    public PersonResponseDto entityToDto(Person person){
        if (person == null) return null;

        return PersonResponseDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDate(person.getBirthDate())
                .gender(person.getGender())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .active(person.getActive())
                .passport(person.getPassport() != null ?
                        passportMapper.entityToDto(person.getPassport()) : null)
                .build();
    }


    public Person dtoToEntity(PersonRequestDto personRequestDto){
        if (personRequestDto == null) return null;

        return Person.builder()
                .firstName(personRequestDto.getFirstName())
                .lastName(personRequestDto.getLastName())
                .birthDate(personRequestDto.getBirthDate())
                .gender(personRequestDto.getGender())
                .email(personRequestDto.getEmail())
                .phoneNumber(personRequestDto.getPhoneNumber())
                .build();
    }


}


