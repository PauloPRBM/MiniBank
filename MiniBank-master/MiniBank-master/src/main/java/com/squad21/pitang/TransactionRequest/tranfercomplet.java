
package com.squad21.pitang.TransactionRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySourceIdOrDestinationId(UUID sourceId, UUID destinationId);
}

@Service
class MovimentacaoService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> listarMovimentacoes(UUID accountId) {
        return transactionRepository.findBySourceIdOrDestinationId(accountId, accountId);
    }
}

@RestController
@RequestMapping("/movimentacoes")
class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> obterMovimentacoes(@PathVariable UUID accountId) {
        List<Transaction> transacoes = movimentacaoService.listarMovimentacoes(accountId);
        return ResponseEntity.ok(transacoes);
    }
}
/ Complemento para o módulo de transferências

package com.squad21.pitang.TransactionRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySourceIdOrDestinationId(UUID sourceId, UUID destinationId);
}

@Service
class MovimentacaoService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> listarMovimentacoes(UUID accountId) {
        return transactionRepository.findBySourceIdOrDestinationId(accountId, accountId);
    }
}

@RestController
@RequestMapping("/movimentacoes")
class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> obterMovimentacoes(@PathVariable UUID accountId) {
        List<Transaction> transacoes = movimentacaoService.listarMovimentacoes(accountId);
        return ResponseEntity.ok(transacoes);
    }
}