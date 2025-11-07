package com.github.phenriqued.api_pagamento.controllers.pagamentosController;

import com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs.CreatePagamentoDTO;
import com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs.PagamentoDTO;
import com.github.phenriqued.api_pagamento.services.pagamentosService.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> createPayment(@RequestBody @Valid CreatePagamentoDTO pagamentoDTO, UriComponentsBuilder builder){
        var pagamento = pagamentoService.createPayment(pagamentoDTO);
        URI uri = builder.path("/pagamentos/{id}").buildAndExpand(pagamento.id()).toUri();
        return ResponseEntity.created(uri).body(pagamento);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAllById(@PageableDefault(size = 5, sort = "id") Pageable pageable){
        return ResponseEntity.ok(pagamentoService.getAllPayments(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable(name = "id") @NotNull Long id){
        return ResponseEntity.ok(pagamentoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePayment(@PathVariable(name = "id") @NotNull Long id, @RequestBody @Valid CreatePagamentoDTO pagamentoDTO){
        pagamentoService.updatePayment(id, pagamentoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable(name = "id") @NotNull Long id){
        pagamentoService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/aprovado")
    @CircuitBreaker(name = "atualizar-pedido", fallbackMethod = "atualizarPagamentoIntegracaoPendente")
    public ResponseEntity<Void> pagamentoAprovado(@PathVariable(name = "id") @NotNull Long id){
        pagamentoService.updateAprovado(id);
        return ResponseEntity.noContent().build();
    }

    public void atualizarPagamentoIntegracaoPendente(Long id, Exception exception){
        pagamentoService.updateIntegracaoPedente(id);
    }


}
