package com.human.passport.services;

import com.human.passport.dto.request.PersonRequestDto;
import com.human.passport.dto.response.PersonResponseDto;
import com.human.passport.utils.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void addNewPerson_shouldReturnDto() {
        PersonRequestDto dto = PersonRequestDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .birthDate(LocalDate.of(1995, 5, 5))
                .gender(Gender.FEMALE)
                .email("jane@example.com")
                .phoneNumber("555123456")
                .build();

        PersonResponseDto response = personService.addNewPerson(dto);

        assertNotNull(response.getId());
        assertEquals("Jane", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }
}
