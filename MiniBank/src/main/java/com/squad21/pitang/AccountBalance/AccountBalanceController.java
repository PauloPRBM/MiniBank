package com.squad21.pitang.AccountBalance;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/account")
public class AccountBalanceController {
@Autowired
private AccountBalanceService accountBalanceService;
@GetMapping("/{id}/balance")
public String seeTheBalance(@PathVariable UUID id){
    return accountBalanceService.findBalance(id);
}

}
