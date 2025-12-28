package com.human.passport.dto.request;

import com.human.passport.utils.enums.PassportType;
import com.human.passport.validation.ValidPassportDates;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidPassportDates
public class PassportRequestDto {

    @NotBlank(message = "Serial number is mandatory")
    private String serialNumber;

    @NotBlank(message = "Passport number is mandatory")
    private String passportNumber;

    @NotBlank(message = "Issuing country is mandatory")
    private String issuingCountry;

    @NotNull(message = "Issue date is mandatory")
    private LocalDate issueDate;

    @NotNull(message = "Expire date is mandatory")
    private LocalDate expireDate;

    @NotNull(message = "Passport type is mandatory")
    private PassportType passportType;

    private Long personId;
}
