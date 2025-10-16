package com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs;

import com.github.phenriqued.api_pagamento.models.pagamentos.Pagamento;
import com.github.phenriqued.api_pagamento.models.pagamentos.Status;

import java.math.BigDecimal;


public record PagamentoDTO(
        Long id,
        BigDecimal valor,
        String nome,
        String numero,
        String expiracao,
        String codigo,
        Status status,
        Long pedidoId,
        Long formaDePagamentoId) {

    public PagamentoDTO(Pagamento entity){
        this(entity.getId(), entity.getValor(), entity.getNome(), entity.getNumero(), entity.getExpiracao(), entity.getCodigo(),
                entity.getStatus(), entity.getPedidoId(), entity.getFormaDePagamentoId());
    }
}
