package com.rabo.emi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String fieldName;
    private String errorMessage;
}
