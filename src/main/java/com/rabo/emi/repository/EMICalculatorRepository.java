package com.rabo.emi.repository;

import com.rabo.emi.model.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMICalculatorRepository extends JpaRepository<LoanDetails, Integer> {
}
