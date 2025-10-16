package com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs;

import com.github.phenriqued.api_pagamento.models.pagamentos.Pagamento;
import com.github.phenriqued.api_pagamento.models.pagamentos.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Valid
public record CreatePagamentoDTO(
        @NotNull
        @PositiveOrZero
        BigDecimal valor,
        @NotBlank
        @Size(max = 100)
        String nome,
        @NotBlank
        @Size(max = 19)
        String numero,
        @NotBlank
        @Size(max = 8)
        String expiracao,
        @NotBlank
        @Size(min = 3, max = 3)
        String codigo,
        @NotNull
        Status status,
        @NotNull
        Long pedidoId,
        @NotNull
        Long formaDePagamentoId) {

    public CreatePagamentoDTO(@NotNull Pagamento entity){
        this(entity.getValor(), entity.getNome(), entity.getNumero(), entity.getExpiracao(), entity.getCodigo(),
                entity.getStatus(), entity.getPedidoId(), entity.getFormaDePagamentoId());
    }
}
