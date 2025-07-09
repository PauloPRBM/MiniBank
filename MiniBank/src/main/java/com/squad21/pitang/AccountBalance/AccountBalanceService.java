package com.squad21.pitang.AccountBalance;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad21.pitang.User.Client.ClientController.ClientRepository.ClientRepository;

@Service
public class AccountBalanceService {
@Autowired
private ClientRepository clientRepository;
public String findBalance(UUID id){
return "balance: " + clientRepository.findBalanceById(id);// It will return the balance by the id
}
}
