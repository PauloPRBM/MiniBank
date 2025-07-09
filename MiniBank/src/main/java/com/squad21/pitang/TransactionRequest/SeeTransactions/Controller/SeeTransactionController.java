package com.squad21.pitang.TransactionRequest.SeeTransactions.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.squad21.pitang.TransactionRequest.SeeTransactions.Service.SeeTransactionsService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/seeTransactions/{numberAccount}")
public class SeeTransactionController {
    @Autowired
    SeeTransactionsService seeTransactionsService;
    @GetMapping("")
    public ResponseEntity<?> SeeTransactionsController(@PathVariable Long numberAccount) throws Exception{
        return seeTransactionsService.seeTransactions(numberAccount);
        //
    }
}
