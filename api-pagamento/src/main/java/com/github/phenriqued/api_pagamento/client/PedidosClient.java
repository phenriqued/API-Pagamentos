package com.github.phenriqued.api_pagamento.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedidos-client", url = "http://localhost:8081/pedidos-ms/pedidos/")
public interface PedidosClient {


    @RequestMapping(method = RequestMethod.PUT, value = "{id}/pago")
    void confirmarPagamento(@PathVariable(value = "id") Long id);

}
