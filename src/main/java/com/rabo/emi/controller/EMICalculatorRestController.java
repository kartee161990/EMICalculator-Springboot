package com.rabo.emi.controller;

import com.rabo.emi.model.LoanDetails;
import com.rabo.emi.service.IEMICalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/loan")
public class EMICalculatorRestController {
    private final IEMICalculatorService emiCalculatorService;


    @PostMapping
    public ResponseEntity<LoanDetails> calculateEmi(@RequestBody @Valid LoanDetails loanDetails) {
        loanDetails = emiCalculatorService.calculateEmiAmount(loanDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(loanDetails);
    }
}
