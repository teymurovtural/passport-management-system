package com.human.passport.controllers;

import com.human.passport.dto.request.PersonRequestDto;
import com.human.passport.dto.response.PersonResponseDto;
import com.human.passport.services.PersonService;
import com.human.passport.utils.enums.Gender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public PersonResponseDto addNewPerson(@Valid @RequestBody PersonRequestDto dto) {
        return personService.addNewPerson(dto);
    }

    @GetMapping
    public List<PersonResponseDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonResponseDto getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}")
    public PersonResponseDto updatePerson(@PathVariable Long id,
                                          @Valid @RequestBody PersonRequestDto dto) {
        return personService.updatePerson(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/search")
    public List<PersonResponseDto> searchPersons(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Gender gender
    ) {
        return personService.searchPersons(firstName, lastName, gender);
    }


    @GetMapping("/search-with-param")
    public Page<PersonResponseDto> searchPersons(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthEnd,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return personService.searchPersons(firstName, lastName, gender, birthStart, birthEnd, page, size, sortBy, direction);
    }


}
