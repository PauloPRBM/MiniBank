package com.squad21.pitang.Loan;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squad21.pitang.User.Client.ClientController.ClientRepository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class LoanService{
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;
    public ResponseEntity<?> createLoan(HttpServletRequest request, HttpServletResponse response, UUID id, 
    LoanDTO loan){
        var ClientAttributes = clientRepository.findById(id);
        if(ClientAttributes.isEmpty()){ // If the user doesn't exist it will not have the permission to acess the route
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user doesn't have permission!");
        } else{ // If exists it will be given permission to the route
        var client = ClientAttributes.get();
        LoanModel loanModel = new LoanModel(client);
        loanModel.setValues(loan.value(), loan.reason(), "PENDENTE"); // It requests and set the status of the user

        var EmprestimoCreated = this.loanRepository.save(loanModel); // Saves in the LoanRepository

        return ResponseEntity.status(HttpStatus.CREATED).body(EmprestimoCreated); // Return in the body the new user
        }
        
    }
        public List<LoanModel> getAllUsers() {
            return loanRepository.findAll(); // Looks for all users that made loan
        }
}
