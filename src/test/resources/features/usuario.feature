Feature: Manter usuários no sistema laserway
  Para validar a funcionalidade usuário
  Como um usuário logado no sistema
  Eu quero validar as ações de cadastros, edição exclusão

  @Usuario
  Scenario: USUÁRIOS - Cadastrar um usuário com sucesso
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Sistema"
    And seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome "Automacao"
    And informar o sobre nome "Teste"
    And informar o email "automacao@cits.br.com"
    And informar o login "automteste"
    And selecionar o grupo "Admin"
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida as informações preenchidas e exibe mensagem "Salvo com sucesso"

  @Smoke
  Scenario: USUÁRIOS - Validar campo nome é obrigatório
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Sistema"
    When seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome ""
    And informar o sobre nome "Teste"
    And informar o email "automacao@cits.br"
    And informar o login "automteste"
    And selecionar o grupo "Admin"
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  @Smoke
  Scenario: USUÁRIOS - Validar campo sobre nome é obrigatório
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Sistema"
    When seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome "Automacao"
    And informar o sobre nome ""
    And informar o email "automacao@cits.br"
    And informar o login "automteste"
    And selecionar o grupo "Admin"
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  @Smoke
  Scenario: USUÁRIOS - Validar campo email é obrigatório
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Sistema"
    When seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome "Automacao"
    And informar o sobre nome "Teste"
    And informar o email ""
    And informar o login "automteste"
    And selecionar o grupo "Admin"
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "O campo é obrigatório."

  @Smoke
  Scenario: USUÁRIOS - Validar campo login usuário é obrigatório
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Sistema"
    When seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome "Automacao"
    And informar o sobre nome "Teste"
    And informar o email "automacao@cits.br"
    And informar o login ""
    And selecionar o grupo "Admin"
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "O campo é obrigatório."

  @Smoke
  Scenario: USUÁRIOS - Validar campo grupo é obrigatório
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Sistema"
    When seleciono o subMenu "Usuário"
    Then visualizo a tela listagem de usuário
    Given selecionar o botão Adicionar Usuário
    And informar o nome "Automacao"
    And informar o sobre nome "Teste"
    And informar o email "automacao@cits.br"
    And informar o login "automteste"
    And selecionar o grupo ""
    And informar os dados de senha "abc1234@" e confirmar senha "abc1234@"
    And selecionar a opção redefinir senha "não"
    When clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "O campo é obrigatório."
