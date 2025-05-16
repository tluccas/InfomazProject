## Projeto criado para resolução dos seguintes desafios seguindo uma planilha com dados

1) Calcule o valor total de venda dos produtos por categoria. Utilize as tabelas CADASTRO_PRODUTOS e TRANSACOES_VENDAS.
2) Calcule a margem dos produtos subtraindo o valor unitario pelo valor de venda. Utilize as tabelas CADASTRO_ESTOQUE e TRANSACOES_VENDAS.
3) Calcule um ranking de clientes por quantidade de produtos comprados por mês. Utilize as tabelas CADASTRO_CLIENTES e TRANSACOES_VENDAS.
4) Calcule um ranking de fornecedores por quantidade de estoque disponivel por mês. Utilize as tabelas CADASTRO_FORNECEDORES e CADASTRO_ESTOQUE.
5) Calcule um ranking de produtos por quantidade de venda por mês. Utilize a tabela TRANSACOES_VENDAS.
6) Calcule um ranking de produtos por valor de venda por mês. Utilize a tabela TRANSACOES_VENDAS.
7) Calcule a média de valor de venda por categoria de produto por mês. Utiliza as tabelas CADASTRO_PRODUTOS e TRANSACOES_VENDAS.
8) Calcule um ranking de margem de lucro por categoria
9) Liste produtos comprados por clientes
10) Ranking de produtos por quantidade de estoque

##  Tecnologias que utilizei

- Java  
  Utilizado para toda a lógica do sistema, seguindo os princípios de Programação Orientada a Objetos (POO) e o padrão MVC (Model-View-Controller), porém devido ao tempo não consegui aplicar todos os principios do MVC entre outros padrões de projeto.

- JDBC (Java Database Connectivity)
  Responsável pela conexão entre o sistema Java e o banco de dados MySQL.

- Maven 
  Gerenciador de dependências utilizado para incluir o driver JDBC e facilitar a organização do projeto.

- MySQL
  Sistema de gerenciamento de banco de dados utilizado para armazenar os dados da aplicação.  
  Os dados das planilhas foram importados para as tabelas por meio do formato CSV.

 -> Os arquivos do banco de dandos encontram-se em CasePeers\infomazDB tanto em formato DUMP quanto físico que o MySQL gera na pasta infomazDB

---

-> O main do projeto está em \infomazProject\src\main\java\org\example\Main.java

Como executar:

1. Configure o banco de dados MySQL com os dados do dump ou arquivos físicos gerados pelo MySQL.
2. Atualize as credenciais de acesso ao banco no código, na clase ConexaoFactory na pasta "util" do projeto.
3. Execute a `Main.java` por a IDE ou no terminal.


