package com.squad21.pitang.TransactionRequest;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;
    public record TransferRequest(
    Long destinationAccount,
    Long sourceAccount,
    BigDecimal value
    ){}
    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        try {
        transferService.transferByAccountNumber(request.sourceAccount(), request.destinationAccount(), request.value());
            return ResponseEntity.ok("Transfer successfull");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"erro\": \"Error: " + e.getMessage() + "\"}");
        }
    } 
    }