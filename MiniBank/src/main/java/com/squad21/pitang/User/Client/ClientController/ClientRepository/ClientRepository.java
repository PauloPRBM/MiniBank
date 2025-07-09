package com.squad21.pitang.User.Client.ClientController.ClientRepository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.squad21.pitang.User.Client.ClientModel.ClientModel;
@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID>{
    ClientModel findByname(String nome);
    ClientModel findByNumberAccount(Long numeroConta);
    ClientModel findByCpf(String cpf);
    @Query("SELECT c.balance FROM Client c WHERE c.id = :id")
    BigDecimal findBalanceById(@Param("id") UUID id);
}
