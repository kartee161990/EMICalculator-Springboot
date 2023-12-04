package com.rabo.emi.service;

import com.rabo.emi.model.LoanDetails;
import com.rabo.emi.repository.EMICalculatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EMICalculatorServiceImpl implements IEMICalculatorService {
    private final EMICalculatorRepository emiCalculatorRepository;
    @Override
    public LoanDetails calculateEmiAmount(LoanDetails loanDetails) {
        var monthlyInterestRate = loanDetails.getInterestRate() / 12 / 100;
        var loanTermMonths = loanDetails.getLoanTerm()*12;
        double emi = (loanDetails.getLoanValue() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths))
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1);
        loanDetails.setEmi((int) Math.round(emi));
        loanDetails.setTimestamp(LocalDateTime.now());

        return emiCalculatorRepository.save(loanDetails);
    }
}
