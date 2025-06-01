## Projeto criado seguindo as seguintes exigências utilizando uma planilha com dados

1) Calculo do valor total de venda dos produtos por categoria.
2) Calculo da margem dos produtos subtraindo o valor unitario pelo valor de venda.
3) Calculo de ranking de clientes por quantidade de produtos comprados por mês.
4) Calculo de ranking de fornecedores por quantidade de estoque disponivel por mês.
5) Calculo de ranking de produtos por quantidade de venda por mês.
6) Calculo de ranking de produtos por valor de venda por mês.
7) Calculo da média de valor de venda por categoria de produto por mês.
8) Calculo de ranking de margem de lucro por categoria.
9) Listagem de produtos comprados por clientes.
10) Ranking de produtos por quantidade de estoque.

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

Como executar:

1. Configure o banco de dados MySQL com os dados do dump ou arquivos físicos gerados pelo MySQL.
2. Atualize as credenciais de acesso ao banco no código, na clase ConexaoFactory na pasta "util" do projeto.
3. Execute a `Main.java` por a IDE ou no terminal.


