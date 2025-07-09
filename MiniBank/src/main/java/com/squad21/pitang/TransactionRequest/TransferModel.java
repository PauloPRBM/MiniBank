package com.squad21.pitang.TransactionRequest;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "TransferRequest")
public class TransferModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID TransferId;
    private Long destinationAccount;
    private Long sourceAccount;
    private BigDecimal value;

    public void setValues(Long sourceAccount, Long destinationAccount, BigDecimal value){
        this.destinationAccount = destinationAccount;
        this.sourceAccount = sourceAccount;
        this.value = value;
    }
}
