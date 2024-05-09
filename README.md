# Teste Técnico Attus 

### Desafio
  - Utilizando seus conhecimentos, desenvolva uma nova funcionalidade de gerenciamento de pessoas, da apresentação da proposta inicial a entrega final do código será [ o digrama de classes, e o código fonte da funcionalidade].

  - A API desenvolvida deve permitir: 
    - Criar, editar e consultar uma ou mais pessoas;
    
    - Criar, editar e consultar um ou mais endereços de uma pessoa; e
    
    - Poder indicar qual endereço será considerado o principal de uma pessoa.
      
### Funcionalidades Implementadas
  - #### Endpoint - Pessoa
    - [x] - Post - (/pessoa) - Para criação de novas entidades de pessoas na API
    - [x] - Get - (/pessoa) - Para busca de pessoa na API: Filtros: por nome e por data de nascimento
    - [x] - Get - (/pessoa/id) - Para busca de pessoa por id específico
    - [x] - Put - (/pessoa/pessoaId/inserir-endereco) - Para para inserir um novo endereço para a entidade pessoa
    - [x] - Put - (/pessoa/id) - Para atualização de uma pessoa na API
    - [x] - Patch - (/pessoa/pessoaId/endereco-principal/enderecoId) - Para informar qual endereço deve ser o endereço principal
  - #### Endpoint - Endereço
    - [x] - Get - (/endereco/enderecoId) - Para buscar um endereço na API com id específico
    - [x] - Get - (/endereco/por-pessoa/pessoaId) - Para buscar os endereços de uma pessoa específica na API com id específico
    - [x] - Put - (/endereco/enderecoId) - Para atualizar um endereço na API com id específico
  - #### Endpoint - Util
      - [x] - Get - (/util/municipios) - Para os municípios: Filtros: por nome e por uf de estado
      - [x] - Get - (/util/estados) - Para os municípios: Filtro de nome ou uf de estado
### Documentação da API
  - Todos os endpoints da API podem ser testados ao subir a aplicação e verificar o endereço local: "http://localhost:8080/swagger-ui/index.html#/"
    
  ![Imagem do swagger](https://github.com/jardessonrb/attus-teste-tecnico/blob/main/src/main/resources/prints/swagger.png)

### Como executar a aplicação
### Pré-requisitos
  - Certifique-se de ter instalado em sua máquina:
  
  - Git
  - JDK (Java Development Kit)
  - Maven
  
  ### Baixando o Repositório

  Em sua pasta de preferência:
   ``` bash
  cd suapasta/
  $suapasta/

  ```
  
  Em sua pasta $suapasta cole o código e execute:
  
  ``` bash
  git clone https://github.com/jardessonrb/attus-teste-tecnico.git

  ```
  Entre na pasta com nome: attus-teste-tecnico
  
  ``` bash
  $suapasta/ cd attus-teste-tecnico
  $attus-teste-tecnico/

  ```
  Dentro da pasta você pode executar o seguinte comando para buildar a aplicação pela primeira vez:
   ``` bash
  mvn package

  ```
  Nessa etapa você deverá ver algo como
  
  ![Imagem do buil com sucesso](https://github.com/jardessonrb/attus-teste-tecnico/blob/main/src/main/resources/prints/build_success.png)

  Feito isso, você pode verificar o coverage da aplicação acessando o index.html dentro da target/site/jacoco/index.html

  ![Imagem do index-coverage](https://github.com/jardessonrb/attus-teste-tecnico/blob/main/src/main/resources/prints/index-coverage.png)

  Quando aberto no navegador, o resulto deverá ser esse:

  ![Imagem do coverage](https://github.com/jardessonrb/attus-teste-tecnico/blob/main/src/main/resources/prints/coverage.png)

  Por fim, basta executar o seguinte comando para subir a aplicação de fato:

   ``` bash
  mvn spring-boot:run

  ```

  
    
