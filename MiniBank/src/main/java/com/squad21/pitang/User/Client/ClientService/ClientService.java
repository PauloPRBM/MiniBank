package com.squad21.pitang.User.Client.ClientService;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.squad21.pitang.User.Client.ClientController.ClientRepository.*;
import com.squad21.pitang.User.Client.ClientModel.ClientModel;
import com.squad21.pitang.User.UserDTO.*;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
    public class ClientService {
    @Autowired
        private ClientRepository clientRepository;

        public ResponseEntity<?> createUser(UserDTO data){
        
        var user_cpf = clientRepository.findByCpf(data.cpf());
        if(user_cpf != null){
            return ResponseEntity.
            status(HttpStatus.BAD_REQUEST)
            .body("{\"error\": \"This CPF already exists!\"}");
        }

        ClientModel newClient = new ClientModel();
        newClient.setName(data.name());
        newClient.setCpf(data.cpf());
        newClient.setEmail(data.email());
        newClient.setAddress(data.address());
        newClient.setBalance(new BigDecimal("50.00"));
        newClient.setNumberAccount(new Random().nextLong(900000) + 100000); // entre 100000 e 999999

        var passwordHashred = BCrypt.withDefaults().
            hashToString(12, 
            data.password().
            toCharArray());
            newClient.setPassword(passwordHashred);

        var clientCreated = this.clientRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }
        public void setDados(String nome, String email, String cpf, String endereco, BigDecimal saldo, 
        Long numeroConta){
            
    }
}