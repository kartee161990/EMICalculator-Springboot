package com.rabo.emi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "LoanDetails")
@Entity
@Builder
public class LoanDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "Car|Home|Personal", message = "Loan Type must be either Car or Home or Personal")
    private String loanType;

    // Assumed that the maximum loan value is 1 billion
    @Range(min=1, max=1000000000, message = "Invalid Loan value")
    private Double loanValue;

    @Range(min=1, max=100, message = "Interest rate must be between 1 and 100")
    private Double interestRate;

    @Range(min=1, max=30, message = "Loan term must be between 1 and 30")
    private Integer loanTerm;

    @NotEmpty
    @Email
    private String email;

    private Integer emi;

    private LocalDateTime timestamp;

    public LoanDetails(String loanType, Double loanValue, Double interestRate, Integer loanTerm, String email, Integer emi, LocalDateTime timestamp) {
        this.loanType = loanType;
        this.loanValue = loanValue;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.email = email;
        this.emi = emi;
        this.timestamp = timestamp;
    }

}
