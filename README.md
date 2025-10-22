## 🎯 Estudando Microsserviços com Spring Cloud

Este repositório é um **laboratório prático** e focado na exploração de padrões de arquitetura de **Microsserviços**, utilizando o ecossistema **Java** e **Spring Boot/Spring Cloud**.

O projeto simula uma **API de Pagamentos** simples, decomposta em serviços menores, para fornecer um ambiente de estudo robusto sobre as principais ferramentas necessárias em um sistema distribuído moderno.

### 💡 Foco e Objetivos de Aprendizado

O foco é implementar e dominar os seguintes conceitos e ferramentas essenciais do **Spring Cloud**:

| Conceito / Padrão | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Service Discovery** | **Eureka Server/Client** | Habilitar a descoberta e registro automático de todos os microsserviços na rede. |
| **Gateway de API** | **Spring Cloud Gateway** | Centralizar o acesso externo, rotear requisições e aplicar políticas (autenticação, rate limiting) em um ponto único. |
| **Comunicação Síncrona** | **Open Feign** | Implementar chamadas HTTP síncronas de forma declarativa e eficiente entre os microsserviços. |
| **Tolerância a Falhas** | **Circuit Breaker** (ex: Resilience4j) | Entender e aplicar o padrão Circuit Breaker para evitar o colapso do sistema em cascata durante falhas de serviço e implementar lógicas de *fallback*. |
| **Persistência** | **MySQL** | Conectar e gerenciar o banco de dados dedicado de um dos microsserviços. |
