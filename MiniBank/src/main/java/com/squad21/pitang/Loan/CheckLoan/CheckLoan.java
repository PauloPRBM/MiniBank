package com.squad21.pitang.Loan.CheckLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad21.pitang.Loan.LoanService;

@RestController
@RequestMapping("")
public class CheckLoan {
    @Autowired
    LoanService loanService;
    @GetMapping("/checkLoan")
    public ResponseEntity<?> checkLoan(){
        return ResponseEntity.status(200).body(loanService.getAllUsers());
    }
    }
