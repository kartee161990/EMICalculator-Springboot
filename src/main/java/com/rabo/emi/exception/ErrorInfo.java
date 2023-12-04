package com.rabo.emi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String fieldName;
    private String errorMessage;
}
