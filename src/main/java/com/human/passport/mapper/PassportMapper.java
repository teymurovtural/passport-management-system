package com.human.passport.mapper;


import com.human.passport.dto.request.PassportRequestDto;
import com.human.passport.dto.response.PassportResponseDto;
import com.human.passport.entities.Passport;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class PassportMapper {


    public PassportResponseDto entityToDto(Passport passport) {
        if(passport == null) return null;

        return PassportResponseDto.builder()
                .id(passport.getId())
                .serialNumber(passport.getSerialNumber())
                .passportNumber(passport.getPassportNumber())
                .issuingCountry(passport.getIssuingCountry())
                .issueDate(passport.getIssueDate())
                .expireDate(passport.getExpireDate())
                .passportType(passport.getPassportType())
                .active(passport.getActive())
                .personId(passport.getPerson() != null ? passport.getPerson().getId() : null)
                .personFullName(passport.getPerson() != null
                        ? passport.getPerson().getFirstName() + " " + passport.getPerson().getLastName()
                        : null)
                .build();
    }


    public Passport dtoToEntity(PassportRequestDto passportRequestDto){
        if (passportRequestDto == null) return null;

        return Passport.builder()
                .serialNumber(passportRequestDto.getSerialNumber())
                .passportNumber(passportRequestDto.getPassportNumber())
                .issuingCountry(passportRequestDto.getIssuingCountry())
                .issueDate(passportRequestDto.getIssueDate())
                .expireDate(passportRequestDto.getExpireDate())
                .passportType(passportRequestDto.getPassportType())
                .build();
    }



}
