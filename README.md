# Real-Time Leaderboard API

Uma API REST de alta performance desenvolvida em **Java** com **Spring Boot**, **Redis** e **MongoDB**, projetada para gerenciar placares e rankings em tempo real de múltiplos jogos simultâneos.

O sistema utiliza uma **arquitetura híbrida**: aproveita a velocidade na memória RAM do **Redis (Sorted Sets)** para cálculos instantâneos de ranking $O(\log N)$, enquanto utiliza o **MongoDB** para persistência do histórico completo de pontuações e perfis de usuários.

---

## Principais Funcionalidades

- **Autenticação & Segurança:** Cadastro e login de usuários com senhas criptografadas via Spring Security.
- **Registro de Pontuações:** Processamento de eventos de pontuação salvando o histórico no MongoDB e atualizando a posição no Redis em tempo real.
- **Posição Individual (`/me`):** Consulta individual onde o jogador descobre sua colocação exata e pontuação acumulada instantaneamente através do comando `ZREVRANK` do Redis.
- **Validação & Tratamento de Erros:** Respostas HTTP padronizadas via `@RestControllerAdvice` com validação de payload em tempo de requisição, evitando erros não tratados.
- **Carga de Dados Idempotente:** Script de inicialização (`CommandLineRunner`) que popula dados de teste de forma inteligente sem duplicar registros no *restart* do servidor.

---

## Tecnologias Utilizadas

- **Linguagem:** Java 21+
- **Framework:** Spring Boot 4
- **Cache & In-Memory DB:** Redis (`StringRedisTemplate` com `ZSet`)
- **NoSQL Database:** MongoDB (`Spring Data MongoDB`)
- **Segurança:** Spring Security / BCrypt
- **Ferramentas:** Lombok, Maven

---

## Arquitetura e Decisões de Design

| Componente | Função no Sistema | Por que essa escolha? |
| :--- | :--- | :--- |
| **Redis (Sorted Set)** | Gestão de Leaderboards | Garante consultas de posição e ordenação dinâmica em tempo real com complexidade $O(\log N)$, evitando gargalos de `ORDER BY` em banco tradicional. |
| **MongoDB** | Persistência do Histórico | Permite salvar o histórico bruto de todas as pontuações e perfis de usuários com alta velocidade de escrita e flexibilidade NoSQL. |

---

## Endpoints Principais

### Autenticação
- `POST /auth/register` — Cadastro de novos jogadores
- `POST /auth/login` — Autenticação e geração de credenciais

### Leaderboard
- `POST /pontuacoes` — Registra uma nova pontuação para um jogador em um jogo específico.
- `GET /leaderboard/{jogo}` — Retorna o ranking global ordenado de um determinado jogo.
- `GET /leaderboard/{jogo}/me` — Retorna a posição, pontuação e dados do jogador autenticado naquele jogo.

#### Exemplo de Resposta do `/leaderboard/{jogo}/me`:
```json
{
  "nickname": "LucasSilva",
  "posicao": 9,
  "pontos": 3624.0,
  "jogo": "XADREZ"
}
```

## Como Rodar o Projeto

### Pré-requisitos
- **Java 21** ou superior
- **Docker** e **Docker Compose**

---

### Passos para Execução

1. **Clone o repositório e acesse a pasta:**
   ```bash
   git clone https://github.com/Arthur32p/Real-time-Leaderboard.git
   cd Real-time-Leaderboard

2. **Suba as instâncias de banco com Docker:**
   ```bash
   docker-compose up -d

3. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run

(O script CargaDadosDataLoader irá popular o banco com 10 usuários base e pontuações de teste automaticamente na primeira inicialização)
   
