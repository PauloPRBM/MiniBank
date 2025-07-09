package com.squad21.pitang.TransactionRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TransferRepository extends JpaRepository<TransferModel, UUID> {
List <TransferModel> findDistinctAccountByDestinationAccountOrSourceAccount(Long sourceAccount, Long DestinationAccount);
}