package com.squad21.pitang.Loan.Modifyloan;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class ModifyLoanController {

    @Autowired
    private ModifyLoanService modifyLoanService;

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatuss(@PathVariable UUID id, @RequestBody LoanStatusDTO dto) {
        return modifyLoanService.updateStatus(id, dto);
    }
}
