# Pizza Ordering

## Diagrama de Classe

````mermaid
classDiagram
  class Usuario {
    id: int
    nome: string
    email: string
    senha: string
    endereco: string
    telefone: string
  }
  class Pedido {
    id: int
    data: datetime
    status: string
    valorTotal: double
    formaPagamento: string
    formaEntrega: string
    observacoes: string
  }
  class Pizza {
    id: int
    nome: string
    descricao: string
    precoBase: double
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
  class Bebida {
    id: int
    nome: string
    descricao: string
    preco: double
  }
  class PizzaPedido {
    quantidade: int
    precoTotal: double
  }
  class BebidaPedido {
    quantidade: int
    precoTotal: double
  }

  Usuario "1" -- "0..*" Pedido : faz
  Pedido "1" -- "1..*" PizzaPedido : contém
  Pedido "1" -- "1..*" BebidaPedido : contém
  Pizza "1" -- "1..*" Tamanho : tem
  Pizza "1" -- "1..*" Sabor : tem
  Pizza "1" -- "1" Borda : tem
  PizzaPedido "1" -- "1" Pizza : ref
  PizzaPedido "1" -- "1" Tamanho : ref
  BebidaPedido "1" -- "1" Bebida : ref

````