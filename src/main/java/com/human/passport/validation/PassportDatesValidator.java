package com.human.passport.validation;

import com.human.passport.dto.request.PassportRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PassportDatesValidator implements ConstraintValidator<ValidPassportDates, PassportRequestDto> {

    @Override
    public boolean isValid(PassportRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getIssueDate() == null || dto.getExpireDate() == null) return true; // @NotNull ayrıca yoxlayır
        return dto.getExpireDate().isAfter(dto.getIssueDate());
    }
}
