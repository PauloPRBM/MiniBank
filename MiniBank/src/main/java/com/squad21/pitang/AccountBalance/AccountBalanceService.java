package com.squad21.pitang.AccountBalance;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squad21.pitang.User.Client.ClientController.ClientRepository.ClientRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AccountBalanceService {
@Autowired
private ClientRepository clientRepository;
public ResponseEntity<?> getSaldo(UUID id, HttpServletRequest request, HttpServletResponse response){
var findUserbyNumerConta = clientRepository.findById(id);
var saldo = request.getAttribute("saldo");
if(findUserbyNumerConta.isEmpty()){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
}
return ResponseEntity.status(200).body("saldo:" + saldo);
}
}
