package com.human.passport.services;

import com.human.passport.dto.request.PassportRequestDto;
import com.human.passport.dto.response.PassportResponseDto;
import com.human.passport.entities.Passport;
import com.human.passport.utils.enums.PassportType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface PassportService {

    PassportResponseDto addNewPassport(PassportRequestDto passportRequestDto);
    PassportResponseDto updatePassport(Long id, PassportRequestDto dto);
    void deletePassport(Long id);
    PassportResponseDto getPassportById(Long id);
    List<PassportResponseDto> getAllPassports();
    List<PassportResponseDto> searchPassports(String serialNumber, PassportType type, Long personId);
    public void deleteSoftPassport(Long id);

    public Page<PassportResponseDto> searchPassports(
            String serialNumber,
            PassportType passportType,
            Long personId,
            LocalDate issueStart,
            LocalDate issueEnd,
            LocalDate expireStart,
            LocalDate expireEnd,
            int page,
            int size,
            String sortBy,
            String direction
    );




}
