# Pizza Ordering

## Diagrama de Classe

````mermaid
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
            +adicionarPizza(pizza, tamanho, quantidade): void
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

````