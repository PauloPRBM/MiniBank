package com.squad21.pitang.Loan;
import java.math.BigDecimal;

import jakarta.validation.constraints.*;
public record LoanDTO(
        @NotNull(message = "Input the value")
        BigDecimal value,

        @NotEmpty(message = "Enter the reason.")
        String reason
){   
}
