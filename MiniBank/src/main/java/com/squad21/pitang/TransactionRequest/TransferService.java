package com.squad21.pitang.TransactionRequest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad21.pitang.User.Client.ClientModel.ClientModel;

import jakarta.persistence.EntityManager; /* O EntityManager é a principal interface para interagir com o banco de dados, 
                                          permitindo criar, ler, atualizar e deletar entidades. */
import jakarta.persistence.PersistenceContext; /* 
                                                                                                 */
import jakarta.transaction.Transactional;
import lombok.Data;
@Service
@Data
public class TransferService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    TransferRepository transferepository;
    @Transactional
    public void transferByAccountNumber(Long sourceAccount, Long destinationAccount, BigDecimal value){
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The value of the transference must be above zero.");
        }
        // Search for sourceAccount and DestinatinoAccount
        ClientModel source = em.createQuery("SELECT c FROM Client c WHERE c.numberAccount = :num", ClientModel.class)
                               .setParameter("num", sourceAccount)
                               .getSingleResult();

        ClientModel destination = em.createQuery("SELECT c FROM Client c WHERE c.numberAccount = :num", ClientModel.class)
                                .setParameter("num", destinationAccount)
                                .getSingleResult();

        // Validate if the balance is enough to the transference
        if (source.getBalance().compareTo(value) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        }

        // Update the balance
        source.setBalance(source.getBalance().subtract(value));
        destination.setBalance(destination.getBalance().add(value));
        // Save the datas
        em.merge(source);
        em.merge(destination);
        TransferModel transfermodel = new TransferModel();

        transfermodel.setSourceAccount(sourceAccount);
        transfermodel.setDestinationAccount(destinationAccount);
        transfermodel.setValue(value);
        
        this.transferepository.save(transfermodel);
}
    }