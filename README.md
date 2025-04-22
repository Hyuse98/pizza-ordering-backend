# Sistema de Pedidos de Pizza - Backend

Este é um backend para um sistema de pedidos de pizza, desenvolvido com Spring Boot, Spring Modulith e outras tecnologias modernas.

## Visão Geral

O Sistema de Pedidos de Pizza é uma aplicação que permite:
- Autenticação e gerenciamento de clientes
- Catálogo de produtos (pizzas, tamanhos, sabores, bordas)
- Carrinho de compras
- Processamento de pedidos
- Acompanhamento de status de pedidos

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Modulith** para arquitetura modular
- **Spring Data JPA** para persistência
- **Spring Security** com JWT para autenticação
- **Spring HATEOAS** para APIs RESTful
- **RabbitMQ** para mensageria
- **H2 Database** (para desenvolvimento)
- **OpenAPI/Swagger** para documentação da API
- **Flyway** para migração de banco de dados
- **Docker Compose** para serviços de infraestrutura

## Estrutura do Projeto

O projeto segue uma arquitetura modular usando Spring Modulith, organizado em módulos:

```
com.hyuse.pizzaOrderingBackend
├── auth/         # Autenticação e segurança
├── user/         # Gestão de usuários/clientes
├── products/     # Catálogo de produtos (pizzas, sabores, etc.)
├── cart/         # Carrinho de compras
├── order/        # Processamento e gestão de pedidos
├── infra/        # Componentes de infraestrutura
└── config/       # Configurações gerais
```

## Como Executar

### Pré-requisitos
- Java 21
- Docker e Docker Compose (para RabbitMQ)
- Gradle

### Passos para Execução

1. Clone o repositório:
   ```
   git clone <url-do-repositorio>
   cd pizza-ordering-backend
   ```

2. Inicie os serviços de infraestrutura:
   ```
   docker-compose up -d
   ```

3. Execute a aplicação:
   ```
   ./gradlew bootRun
   ```

4. Acesse a documentação da API:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## Documentação do Módulo

Para gerar a documentação do Spring Modulith:

```
./gradlew modulithStructure
./gradlew generateModulithDocs
```

## Diagrama de Classe

```mermaid
classDiagram

namespace Auth_Module{
    class Auth {
        +criarUsuario(email, senha): JWT
        +login(email, senha): JWT
        +verificarToken(JWT): UserInfo
    }

    class JWT {
        // ... informações do token ...
    }
    
}
    namespace User_Module {
        class Cliente {
            id: int
            email: string
            nome: string
            endereco: string
            telefone: string
        }        

        class UserInfo {
            userId: int
            email: string
            // ... outras informações do usuário ...
        }
    }

    namespace Order_Module {
        class Carrinho {
            id: int
            clienteId: int
            itens: list<ItemCarrinho>
            +adicionarPizza(product, tamanho, quantidade): void
            +removerItem(itemCarrinho): void
            +visualizarCarrinho(): list<ItemCarrinho>
            +calcularTotal(): double
            +finalizarPedido(): Pedido
        }
        class ItemCarrinho {
            pizzaId: int
            tamanhoId: int
            quantidade: int
            precoUnitario: double
            precoTotal: double
        }
        class Pedido {
            id: int
            clienteId: int
            data: datetime
            status: string
            valorTotal: double
            formaPagamento: string
            formaEntrega: string
            observacoes: string
            itensPizza: list<PizzaPedido>
            +acompanharEstado(): string
        }
        class PizzaPedido {
            pedidoId: int
            pizzaId: int
            tamanhoId: int
            quantidade: int
            precoUnitario: double
            precoTotal: double
        }
    }

    namespace Product_Module {
        class Pizza {
            id: int
            nome: string
            descricao: string
            precoBase: double
            tamanhos: list<Tamanho>
            sabores: list<Sabor>
            borda: Borda
        }
        class Tamanho {
            id: int
            nome: string
            multiplicadorPreco: double
        }
        class Sabor {
            id: int
            nome: string
            descricao: string
        }
        class Borda {
            id: int
            nome: string
            precoAdicional: double
        }
    }
    
    Cliente "1" -- "1" Carrinho: tem
    Cliente "1" -- "0..*" Pedido: faz
    Carrinho "1" -- "0..*" ItemCarrinho: contém
    Pedido "1" -- "1..*" PizzaPedido: contém
    Pizza "1" -- "*" Tamanho: possui
    Pizza "1" -- "0..*" Sabor: possui
    Pizza "1" -- "0..1" Borda: possui
    ItemCarrinho "1" -- "1" Pizza: ref
    ItemCarrinho "1" -- "1" Tamanho: ref
    PizzaPedido "1" -- "1" Pizza: ref
    PizzaPedido "1" -- "1" Tamanho: ref
    Cliente "1" -- "1" Auth: autentica
```

## API Endpoints

A documentação completa da API está disponível via Swagger/OpenAPI na aplicação em execução. Os principais endpoints incluem:

- `/api/auth` - Endpoints de autenticação
- `/api/users` - Gestão de usuários/clientes
- `/api/products` - Gestão do catálogo de produtos
- `/api/cart` - Operações do carrinho de compras
- `/api/orders` - Gestão de pedidos

## Desenvolvimento

### Adicionar Funcionalidades

Para adicionar novas funcionalidades, recomenda-se seguir a abordagem modular:

1. Identifique o módulo apropriado ou crie um novo se necessário
2. Implemente entidades, repositórios, serviços e controllers conforme necessário
3. Siga os princípios de design SOLID e DDD

## Licença

[Especificar a licença do projeto]