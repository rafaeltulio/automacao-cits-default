Feature: Cadastro de grupo

  @Grupos
  Scenario: Cadastrar grupo com perfil Administrador
    Given o usuário acessar a url e a tela de login é exibida
    When informo os campos de usuario "admin" e senha "recuperar" e clico no botao login
    Given seleciono o menu "Sistema"
    When seleciono o subMenu "Grupos"
    Then clica no botao "Adicionar Grupo"
    And preenche o nome do grupo "Automação"
    And seleciona a lista de conteúdo: "Selecionar todos" e suas permissões "Leitura e escrita"
    And clica no botao "Salvar"
    Then o sistema valida as informacoes e exibe a mensagem "Grupo adicionado com sucesso!"

  @Grupos
  Scenario: Cadastrar grupo com perfil Leitura
    Given o usuário acessar a url e a tela de login é exibida
    When informo os campos de usuario "admin" e senha "recuperar" e clico no botao login
    Given seleciono o menu "Sistema"
    When seleciono o subMenu "Grupos"
    Then clica no botao "Adicionar Grupo"
    And preenche o nome do grupo "Automação"
    And seleciona a lista de conteúdo: "Selecionar todos" e suas permissões "Leitura"
    And clica no botao "Salvar"
    Then o sistema valida as informacoes e exibe a mensagem "Grupo adicionado com sucesso!"

  @Grupos
  Scenario: Cadastrar grupo com perfil Nenhum
    Given o usuário acessar a url e a tela de login é exibida
    When informo os campos de usuario "admin" e senha "recuperar" e clico no botao login
    Given seleciono o menu "Sistema"
    When seleciono o subMenu "Grupos"
    Then clica no botao "Adicionar Grupo"
    And preenche o nome do grupo "Automação"
    And seleciona a lista de conteúdo: "Selecionar todos" e suas permissões "Nenhum"
    And clica no botao "Salvar"
    Then o sistema valida as informacoes e exibe a mensagem "Grupo adicionado com sucesso!"

  @Grupos
  Scenario: Cadastrar grupo com perfil Aleatório
    Given o usuário acessar a url e a tela de login é exibida
    When informo os campos de usuario "admin" e senha "recuperar" e clico no botao login
    Given seleciono o menu "Sistema"
    When seleciono o subMenu "Grupos"
    Then clica no botao "Adicionar Grupo"
    And preenche o nome do grupo "Automação"
    And seleciona a lista de conteúdo: "OLT" e suas permissões "Leitura e escrita"
    And seleciona a lista de conteúdo: "ONUs" e suas permissões "Leitura"
    And seleciona a lista de conteúdo: "Tipo de serviço" e suas permissões "Nenhum"
    And seleciona a lista de conteúdo: "VoIP" e suas permissões "Leitura e escrita"
    And clica no botao "Salvar"
    Then o sistema valida as informacoes e exibe a mensagem "Grupo adicionado com sucesso!"
