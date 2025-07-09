package com.squad21.pitang.Loan.Modifyloan;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squad21.pitang.Loan.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class ModifyLoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Transactional
    public ResponseEntity<?> updateStatus(UUID id, LoanStatusDTO dto) {
        var loanOptional = loanRepository.findById(id); // Looks for the id of the loan
        
        if (loanOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found"); // Checks if exists
        }

        var loan = loanOptional.get();
        loan.setStatus(dto.status());
        var balanceClient = loan.getCliente();
        if("APPROVED".equalsIgnoreCase(dto.status())){
            balanceClient.setBalance(loan.getValue().add(balanceClient.getBalance())); // It will set the new balance if it is approved the loan
        }

        var updated = loanRepository.save(loan); // Modify the loan to "APPROVED"
        return ResponseEntity.ok(updated);
    }
}
