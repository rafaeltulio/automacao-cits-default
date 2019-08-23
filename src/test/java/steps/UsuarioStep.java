package steps;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;
import pageObjects.UsuarioPage;
import setup.RecuperarSenha;
import setup.Senhas;
import util.DriverFactory;
import util.Generica;
import util.Menu;
import util.PropertyReader;

public class UsuarioStep {

	LoginPage loginPage = new LoginPage();
	Menu menuPage = new Menu();
	UsuarioPage usuarioPage = new UsuarioPage();
	Generica generica = new Generica();
	RecuperarSenha recuperarSenha = new RecuperarSenha();
	DriverFactory factory = new DriverFactory();

	static WebDriver driver;

	public UsuarioStep() throws Exception {
		driver = DriverFactory.driver;
		if (driver == null) {
			driver = factory.openBrowser();
		}

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		menuPage = PageFactory.initElements(driver, Menu.class);
		usuarioPage = PageFactory.initElements(driver, UsuarioPage.class);
	}

	@Given("o usuário acesso a url do sistema e preenche os campos {string} e {string}")
	public void efetuarLogin(String usuario, String senha) throws IOException {

		driver.get(new PropertyReader().readProperty("host.laserway"));
		if (senha.equals("recuperar")) {
			senha = recuperarSenha.recuperarSenha(Senhas.APLICACAO.toString());
		}

		loginPage.preencherLogin(usuario, senha);
	}

	@Given("seleciono o menu {string}")

	public void selecionarMenu(String menuPrincipal) {
		menuPage.verificaResolucaoMenu();
		menuPage.selecionarMenuVertical("span", menuPrincipal);

	}

	@When("seleciono o subMenu {string}")
	public void selecionoOSubMenu(String subMenu) {
		menuPage.selecionarMenuVertical("span", subMenu);
	}

	@Then("visualizo a tela listagem de usuário")
	public void validarTelaUsuario() {

	}

	@Given("selecionar o botão Adicionar Usuário")
	public void adicionarUsuario() {
		usuarioPage.adicionarUsuario();

	}

	@Given("informar o nome {string}")
	public void preencherNome(String nome) {
		usuarioPage.enterUser(nome);
	}

	@Given("informar o sobre nome {string}")
	public void preencherSobreNome(String sobreNome) {
		usuarioPage.enterLastName(sobreNome);

	}

	@Given("informar o email {string}")
	public void preencherEmail(String email) {
		usuarioPage.informarEmail(email);

	}

	@Given("informar o login {string}")
	public void preencherLogin(String login) {
		usuarioPage.informarLogin(login);

	}

	@Given("selecionar o grupo {string}")
	public void selecionarGrupo(String group) {
		usuarioPage.selectGroup(group);

	}

	@Given("informar os dados de senha {string} e confirmar senha {string}")
	public void informarSenhas(String senha, String confirmaSenha) {
		usuarioPage.enterSenha(senha, confirmaSenha);

	}

	@Given("selecionar a opção redefinir senha {string}")
	public void redefinirSenha(String redefinirSenha) {
		if (redefinirSenha.equals("não")) {
			usuarioPage.checkRedefinirPrimeiroAcesso.click();
		}

	}

	@When("clicar no botão salvar")
	public void botaoSalvar() {
		usuarioPage.clicarButtonSalvar();

	}

	@Then("o sistema valida as informações preenchidas e exibe mensagem {string}")
	public void validarCadastro(String mensagem) {
		usuarioPage.validarMensagem(driver, mensagem);

	}

	@Then("o sistema valida os campos não preenchidos e exibe a mensagem de alerta {string}")
	public void validacaoCamposMensagem(String mensagemCampos) {
		usuarioPage.validarMensagemCampos(driver, mensagemCampos);

	}

	@Then("o sistema valida os campos e exibe mensagem {string}")
	public void validarCampos(String mensagemCampos) {

		usuarioPage.validarMensagemCampos(driver, mensagemCampos);
		// driver.navigate().refresh();
	}

}