package com.human.passport.dto.response;

import com.human.passport.utils.enums.PassportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassportResponseDto {

    private Long id;
    private String serialNumber;
    private String passportNumber;
    private String issuingCountry;
    private LocalDate issueDate;
    private LocalDate expireDate;
    private PassportType passportType;
    private Boolean active;

    private Long personId;
    private String personFullName;
}
