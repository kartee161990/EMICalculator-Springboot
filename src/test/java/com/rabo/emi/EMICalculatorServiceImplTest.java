package com.rabo.emi;

import com.rabo.emi.model.LoanDetails;
import com.rabo.emi.repository.EMICalculatorRepository;
import com.rabo.emi.service.EMICalculatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EMICalculatorServiceImplTest {
    @Mock
    private EMICalculatorRepository emiCalculatorRepository;

    @InjectMocks
    private EMICalculatorServiceImpl emiCalculatorService;

    @Test
    void testCalculateEmiAmount() {
        var loanDetailsRequest = createRequest();
        var loanDetailsResponse = createResponse();
        when(emiCalculatorRepository.save(any(LoanDetails.class))).thenReturn(loanDetailsResponse);

        LoanDetails result = emiCalculatorService.calculateEmiAmount(loanDetailsRequest);

        verify(emiCalculatorRepository, times(1)).save(any(LoanDetails.class));

        assertEquals(loanDetailsResponse.getEmi(), result.getEmi());
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
