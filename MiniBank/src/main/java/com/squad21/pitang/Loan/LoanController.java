package com.squad21.pitang.Loan;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan/{id}")
public class LoanController {
    @Autowired
    private LoanService emprestimoService;
    @PostMapping("")
        public ResponseEntity<?> createLoan(HttpServletRequest request, HttpServletResponse response, 
        @PathVariable UUID id, @Valid @RequestBody LoanDTO data){
            return emprestimoService.createLoan(request, response, id, data);
    }

}