package com.rabo.emi.service;

import com.rabo.emi.model.LoanDetails;

public interface IEMICalculatorService {
    LoanDetails calculateEmiAmount(LoanDetails loanDetails);
}
