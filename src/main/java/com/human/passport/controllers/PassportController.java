package com.human.passport.controllers;


import com.human.passport.dto.request.PassportRequestDto;
import com.human.passport.dto.response.PassportResponseDto;
import com.human.passport.services.PassportService;
import com.human.passport.utils.enums.PassportType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping
    public PassportResponseDto addNewPassport(@Valid @RequestBody PassportRequestDto dto) {
        return passportService.addNewPassport(dto);
    }

    @GetMapping
    public List<PassportResponseDto> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/{id}")
    public PassportResponseDto getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @PutMapping("/{id}")
    public PassportResponseDto updatePassport(@PathVariable Long id,
                                              @Valid @RequestBody PassportRequestDto dto) {
        return passportService.updatePassport(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }

    @GetMapping("/search")
    public List<PassportResponseDto> searchPassports(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) PassportType type,
            @RequestParam(required = false) Long personId
    ) {
        return passportService.searchPassports(serialNumber, type, personId);
    }


    @GetMapping("/search-with-param")
    public Page<PassportResponseDto> searchPassports(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) PassportType passportType,
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expireStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expireEnd,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return passportService.searchPassports(serialNumber, passportType, personId, issueStart, issueEnd, expireStart, expireEnd, page, size, sortBy, direction);
    }


}