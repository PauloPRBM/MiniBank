package com.squad21.pitang.TransactionRequest.SeeTransactions.Service;
import com.squad21.pitang.TransactionRequest.TransferModel;
import com.squad21.pitang.TransactionRequest.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeeTransactionsService {

    @Autowired
    private TransferRepository transferepository;

    public ResponseEntity<?> seeTransactions(Long numberAccount) {
    List<TransferModel> numberAccount1 = 
    transferepository.findDistinctAccountByDestinationAccountOrSourceAccount(numberAccount, numberAccount); // Looks for the accounts
    List<String> descriptions = new ArrayList<>(); // Create an array type for string with the transfers made and received
    for(TransferModel transfer: numberAccount1)
        if(transfer.getSourceAccount().equals(numberAccount)){ // Looks for the sourceAccount if matchs the numberAccount
                descriptions.add(
                    String.format("Transfer send to the account: %d in the value of: R$ %.2f",
                        transfer.getDestinationAccount(), transfer.getValue())
                );
            } else if(transfer.getDestinationAccount().equals(numberAccount)) { // Looks for the DestinationAccount if matchs the numberAccount
                descriptions.add(
                    String.format("Transfer received from the account: %d in the value of: R$ %.2f",
                        transfer.getSourceAccount(), transfer.getValue())
                );
            }
            return ResponseEntity.ok().body(descriptions);
        }
    }