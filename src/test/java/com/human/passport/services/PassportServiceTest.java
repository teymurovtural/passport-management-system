package com.human.passport.services;

import com.human.passport.dto.request.PassportRequestDto;
import com.human.passport.dto.response.PassportResponseDto;
import com.human.passport.utils.enums.PassportType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PassportServiceTest {

    @Autowired
    private PassportService passportService;

    @Test
    void addNewPassport_shouldReturnDto() {
        PassportRequestDto dto = PassportRequestDto.builder()
                .serialNumber("AA")
                .passportNumber("123456")
                .issuingCountry("USA")
                .issueDate(LocalDate.of(2020, 1, 1))
                .expireDate(LocalDate.of(2030, 1, 1))
                .passportType(PassportType.ORDINARY)
                .build();

        PassportResponseDto response = passportService.addNewPassport(dto);

        assertNotNull(response.getId());
        assertEquals("AA", response.getSerialNumber());
        assertEquals("123456", response.getPassportNumber());
    }
}
