package com.squad21.pitang.Loan;
import java.math.BigDecimal;
import java.util.UUID;

import com.squad21.pitang.User.Client.ClientModel.ClientModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity(name = "LoanClient")
public class LoanModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idLoan;

    @ManyToOne
    private ClientModel cliente;

    private BigDecimal value;
    private String reason;
    private String status;

    public LoanModel(ClientModel cliente) {
        this.cliente = cliente;
    }

    public LoanModel() {
    }

    public void setValues(BigDecimal value, String reason, String status){
        this.value = value;
        this.reason = reason;
        this.status = status;
    }
}