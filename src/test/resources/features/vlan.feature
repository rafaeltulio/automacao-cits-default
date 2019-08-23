Feature: Validar cadastro de vlan
  Para validar a funcionalidade vlans
  Como um usuário logado no sistema 
  Eu quero validar as acções de cadastrar, editar e excluir

  @Vlan
  Scenario: VLANS - Cadastro de VLAN Serviço Internet com sucesso.
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    When seleciono o menu "Configurações"
    And seleciono o sub menu "VLANs"
    Then visualizo a tela listagem de vlans
    Given seleciono o botao Adicionar Vlans
    And seleciono o tipo de serviço "LASERWAY_PC"
    And informo nome vlan "LASERWAY_PC"
    And informo VLAN ID "514"
    And informo campo observacao "Vlan de internet criada com robô de teste"
    And seleciono o botao salvar
    Then o sistema valida as informações preenchidas e exibe mensagem "VLAN adicionada com sucesso!"

  @Vlan
  Scenario: VLANS - Cadastro de VLAN Serviço Telefonia com sucesso.
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Configurações"
    And seleciono o sub menu "VLANs"
    Then visualizo a tela listagem de vlans
    Given seleciono o botao Adicionar Vlans
    And seleciono o tipo de serviço "LASERWAY_VOIP"
    And informo nome vlan "LASERWAY_VOIP"
    And informo VLAN ID "513"
    And informo campo observacao "Vlan voip criada com robô de teste"
    And seleciono o botao salvar
    Then o sistema valida as informações preenchidas e exibe mensagem "VLAN adicionada com sucesso!"

  @Vlan
  Scenario: VLANS - Restrição de nome duplicado
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Configurações"
    And seleciono o sub menu "VLANs"
    Then visualizo a tela listagem de vlans
    Given seleciono o botao Adicionar Vlans
    And seleciono o tipo de serviço "LASERWAY_PC"
    And informo uma vlan com o nome repetido "VLAN_AUTOMACAO"
    And informo VLAN ID "510"
    And informo campo observacao "Vlan criada teste robô automatizado"
    And seleciono o botao salvar
    Then o sistema valida as informações preenchidas e exibe mensagem "Operação não aceita Nome já está em uso"

  @Vlan
  Scenario: VLANS - Restrição de VID duplicado
    Given o usuário acesso a url do sistema e preenche os campos "admin" e "recuperar"
    And seleciono o menu "Configurações"
    And seleciono o sub menu "VLANs"
    Then visualizo a tela listagem de vlans
    Given seleciono o botao Adicionar Vlans
    And seleciono o tipo de serviço "LASERWAY_PC"
    And informo nome vlan "LASERWAY_PC_VID"
    And informo VLAN ID "514"
    And informo campo observacao "Vlan criada teste robô automatizado"
    And seleciono o botao salvar
    Then o sistema valida as informacoes e exibe mensagem "Operação não aceita Vlan ID já está em uso"
