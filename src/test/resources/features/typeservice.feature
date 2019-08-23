Feature: Validar funcionalidade tipo de serviço
  Para validar a funcionalidade tipo serviço
  Como um usuário logado no sistema 
  Eu quero validar as ações de cadastrar, editar e excluir

  @tipoServico
  Scenario: TIPO SERVIÇO - Cadastrar Tipo Serviço de Internet com sucesso
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipos de Serviços"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_AUTOMACAO_PC"
    And informar downstream máximo "1031616"
    And informar assured upstream "512"
    And informar upstream máximo "1031616"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar tipo servico
    #Then o sistema valida as informações preenchidas e exibe mensagem "Tipo de serviço adicionado com sucesso!"

  Scenario: TIPO SERVIÇO - Cadastrar Tipo Serviço voip com sucesso
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_AUTOMACAO_VOIP"
    And informar downstream máximo "2048"
    And informar assured upstream "512"
    And informar upstream máximo "2048"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar

  Scenario: TIPO SERVIÇO - Cadastrar Tipo Serviço ja existente
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço duplicado "SERVICO_INTERNET"
    And informar downstream máximo "2048"
    And informar assured upstream "512"
    And informar upstream máximo "2048"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida as informações preenchidas e exibe mensagem "Operação não aceita Nome já está em uso"

@tpService
  Scenario: TIPO SERVIÇO - Validar campo obrigatório nome tipo serviço
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipos de Serviços"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço ""
    And informar downstream máximo "2048"
    And informar assured upstream "512"
    And informar upstream máximo "2048"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  Scenario: TIPO SERVIÇO - Validar campo obrigatório downstream maximo
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_NOVO"
    And informar downstream máximo ""
    And informar assured upstream "512"
    And informar upstream máximo "2048"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  Scenario: TIPO SERVIÇO - Validar campo obrigatório assured upstream
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_NOVO"
    And informar downstream máximo "1024"
    And informar assured upstream ""
    And informar upstream máximo "2048"
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  Scenario: TIPO SERVIÇO - Validar campo obrigatório upstream maximo
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_NOVO"
    And informar downstream máximo "1024"
    And informar assured upstream "512"
    And informar upstream máximo ""
    And selecionar cos "0"
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."

  Scenario: TIPO SERVIÇO - Validar campo obrigatório cos
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "Tipo de serviço"
    Then visualizo a tela listagem de "Tipos de Serviço"
    Given clicar no botão adicionar tipo serviço
    And informar nome do serviço "SERVICO_NOVO"
    And informar downstream máximo "1024"
    And informar assured upstream "512"
    And informar upstream máximo "1024"
    And selecionar cos ""
    And selecionar dscp "0"
    And clicar no botão salvar
    Then o sistema valida os campos e exibe mensagem "Campo em branco ou somente com espaços."
