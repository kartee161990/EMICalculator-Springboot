package com.rabo.emi;

import com.rabo.emi.controller.EMICalculatorRestController;
import com.rabo.emi.model.LoanDetails;
import com.rabo.emi.service.IEMICalculatorService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EMICalculatorRestControllerTest {

    @InjectMocks
    private EMICalculatorRestController emiCalculatorRestController;

    @Mock
    private IEMICalculatorService emiCalculatorService;

    @Test
    void testCalculateEmiWithValidData() {
        var requestLoanDetails = createRequest();
        var expectedResponseLoanDetails = createResponse();

        when(emiCalculatorService.calculateEmiAmount(any(LoanDetails.class))).thenReturn(expectedResponseLoanDetails);

        var actualResponse = emiCalculatorRestController.calculateEmi(requestLoanDetails);

        assertThat(actualResponse.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        assertEquals(expectedResponseLoanDetails.getEmi(), Objects.requireNonNull(actualResponse.getBody()).getEmi());
        verify(emiCalculatorService, times(1)).calculateEmiAmount(requestLoanDetails);
    }

    private LoanDetails createRequest() {

        return LoanDetails.builder()
                .loanType("Car")
                .loanValue(12990.0)
                .interestRate(11.0)
                .loanTerm(4)
                .email("test@abc.com")
                .build();

    }

    private LoanDetails createResponse() {
        return LoanDetails.builder()
                .loanType("Car")
                .loanValue(12990.0)
                .interestRate(11.0)
                .loanTerm(4)
                .email("test@abc.com")
                .emi(336)
                .build();
    }

}
