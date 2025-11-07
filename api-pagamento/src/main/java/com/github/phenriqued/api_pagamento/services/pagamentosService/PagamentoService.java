package com.github.phenriqued.api_pagamento.services.pagamentosService;

import com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs.CreatePagamentoDTO;
import com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs.PagamentoDTO;
import com.github.phenriqued.api_pagamento.client.PedidosClient;
import com.github.phenriqued.api_pagamento.models.pagamentos.Pagamento;
import com.github.phenriqued.api_pagamento.models.pagamentos.Status;
import com.github.phenriqued.api_pagamento.repositories.pagamentosRepository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PedidosClient pedidosClient;

    public PagamentoService(PagamentoRepository repository, PedidosClient pedidosClient) {
        this.repository = repository;
        this.pedidosClient = pedidosClient;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PagamentoDTO createPayment(CreatePagamentoDTO dto){
        Pagamento payment = new Pagamento(dto);
        repository.save(payment);
        return new PagamentoDTO(payment);
    }

    @Transactional(readOnly = true)
    public List<PagamentoDTO> getAllPayments(Pageable pageable){
        return repository.findAll(pageable).stream().map(PagamentoDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public PagamentoDTO getById(Long id){
        return repository.findById(id).map(PagamentoDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public void updatePayment(Long id, CreatePagamentoDTO dto) {
        Pagamento pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        Pagamento update = new Pagamento(dto);
        pagamento.updatePagamento(update);
        repository.flush();
    }

    public void deletePayment(Long id){
        repository.deleteById(id);
    }

    public void updateAprovado(@NotNull Long id) {
        Pagamento pagamento = updateStatus(id, Status.CONFIRMADO);
        pedidosClient.confirmarPagamento(pagamento.getPedidoId());
    }

    public void updateIntegracaoPedente(Long id) {
        updateStatus(id, Status.CONFIRMADO_SEM_INTEGRACAO);
    }

    private Pagamento updateStatus(Long id, Status status){
        Pagamento pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        pagamento.setStatus(status);
        repository.flush();
        return pagamento;
    }

}
