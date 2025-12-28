package com.human.passport.dto.response;

import com.human.passport.utils.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Boolean active;

    private PassportResponseDto passport;
}
