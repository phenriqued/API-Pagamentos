package com.github.phenriqued.api_pagamento.repositories.pagamentosRepository;

import com.github.phenriqued.api_pagamento.models.pagamentos.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
