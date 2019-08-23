Feature: Realizar autenticação do usuário no sistema laserway
  Para validar a autenticação no sistema
  Como usuário do sistema
  Eu quero logar e validar acesso aos menus da aplicação

  Background: Acessar o sistema laserway
    Given o usuário acessar a url e a tela de login é exibida

  @Smoke
  Scenario Outline: LOGIN - Autenticação com dados invalidos
    When informo os campos de usuario "<usuario>" e senha "<senha>" e clico no botao login
    Then o sistema valida as informações preenchidas exibindo mensagem "<mensagem>"

    Examples: 
      | usuario | senha    | mensagem                      |
      | admin   |   123456 | Invalid username or password. |
      | login   | au5dwfk0 | Invalid username or password. |
      |         | au5dwfk0 | Invalid username or password. |
      |         |          | Invalid username or password. |

  @Login
  Scenario Outline: LOGIN - Realizar autenticação com sucesso
    When informo os campos de usuario "<usuario>" e senha "<senha>" e clico no botao login
    Then o sistema efetua o login com sucesso e valida o usuário logado "<usuario>"

    Examples: 
      | usuario | senha     |
      | admin   | recuperar |
