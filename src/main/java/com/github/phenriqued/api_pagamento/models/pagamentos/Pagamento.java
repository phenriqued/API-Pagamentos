package com.github.phenriqued.api_pagamento.models.pagamentos;

import com.github.phenriqued.api_pagamento.DTOs.pagamentoDTOs.CreatePagamentoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_pagamentos")

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal valor;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 19)
    private String numero;

    @NotBlank
    @Column(nullable = false, length = 8)
    private String expiracao;

    @NotBlank
    @Size(min = 3, max = 3)
    @Column(nullable = false, length = 3)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long formaDePagamentoId;

    public Pagamento(CreatePagamentoDTO dto){
        this.valor = dto.valor();
        this.nome = dto.nome();
        this.numero = dto.numero();
        this.expiracao = dto.expiracao();
        this.codigo = dto.codigo();
        this.status = dto.status();
        this.pedidoId = dto.pedidoId();
        this.formaDePagamentoId = dto.formaDePagamentoId();
    }

    public void updatePagamento(Pagamento pagamento){
        setValor(pagamento.getValor());
        setNome(pagamento.getNome());
        setNumero(pagamento.getNumero());
        setExpiracao(pagamento.getExpiracao());
        setCodigo(pagamento.getCodigo());
        setStatus(pagamento.getStatus());
        setPedidoId(pagamento.getPedidoId());
        setFormaDePagamentoId(pagamento.getFormaDePagamentoId());
    }


}
