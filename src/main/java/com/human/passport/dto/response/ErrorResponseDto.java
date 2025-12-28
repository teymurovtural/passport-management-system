package com.human.passport.dto.response;

import com.human.passport.utils.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private ErrorCode code;
    private String message;
    private LocalDateTime timestamp;

}
