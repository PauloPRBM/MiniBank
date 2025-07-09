package com.squad21.pitang.User.Manager.ManagerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.squad21.pitang.User.Manager.ManagerModel.MnModel;
import com.squad21.pitang.User.Manager.ManagerRepository.MnRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.annotation.PostConstruct;

@Component
    public class CreateManager {
    @Autowired
        private MnRepository managerRepository;
        @PostConstruct
        public void Create_Manager(){
        if(managerRepository.findByname("manager") == null){
        MnModel newClient = new MnModel();
        newClient.setName("manager");
        newClient.setPassword("password");
        
        var passwordHashred = BCrypt.withDefaults().
            hashToString(12, 
            newClient.getPassword().
            toCharArray());
            newClient.setPassword(passwordHashred);

            managerRepository.save(newClient);
        }
    }
    }