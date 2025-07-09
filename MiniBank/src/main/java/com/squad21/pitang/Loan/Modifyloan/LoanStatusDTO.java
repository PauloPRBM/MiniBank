package com.squad21.pitang.Loan.Modifyloan;

import jakarta.validation.constraints.NotEmpty;

public record LoanStatusDTO(
    @NotEmpty(message = "Enter the status that you want to modify")
    String status
){    
}