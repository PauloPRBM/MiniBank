package com.squad21.pitang.AccountBalance;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/conta")
public class AccountBalanceController {
@Autowired
private AccountBalanceService accountBalanceService;
@GetMapping("/{id}/saldo")
public ResponseEntity<?> verSaldo(@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response){
    return accountBalanceService.getSaldo(id, request, response);
}

}
