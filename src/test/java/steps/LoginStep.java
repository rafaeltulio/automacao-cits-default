package steps;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;
import setup.RecuperarSenha;
import setup.Senhas;
import util.DriverFactory;
import util.PropertyReader;

public class LoginStep {

	LoginPage loginPage = new LoginPage();
	static WebDriver driver;
	WebDriverWait wait = null;
	DriverFactory factory = new DriverFactory();
	RecuperarSenha recuperarSenha = new RecuperarSenha();
	Logger logger = Logger.getLogger(LoginStep.class.toString());

	public LoginStep() throws Exception {
		driver = DriverFactory.driver;
		if (driver == null) {
			driver = factory.openBrowser();
		}

		loginPage = PageFactory.initElements(driver, LoginPage.class);
	}

	@Before
	public void beforScenario(Scenario scenario) {
		logger.info("------------------------ Iniciando Cenário : " + scenario.getId() + " - " + scenario.getName());

	}

	@After
	public void afterScenario(Scenario scenario) throws Exception {
		factory.embedScreenshot(scenario);
		logger.info("------------------------ Finalizando Cenário: " + scenario.getId() + " - " + scenario.getName());
	}

	@Given("o usuário acessar a url e a tela de login é exibida")
	public void acessarUrlSistema() {
		driver.get(new PropertyReader().readProperty("host.laserway"));
	}

	@When("informo os campos de usuario {string} e senha {string} e clico no botao login")
	public void informarLogin(String usuario, String senha) throws IOException {

		if (senha.equals("recuperar")) {
			senha = recuperarSenha.recuperarSenha(Senhas.APLICACAO.toString());
		}

		loginPage.preencherLogin(usuario, senha);
	}

	@Then("o sistema valida as informações preenchidas exibindo mensagem {string}")
	public void validarMensagemRetorno(String mensagemRetorno) {

		loginPage.capturarMensagemRetorno(mensagemRetorno);

	}

	@Then("o sistema efetua o login com sucesso e valida o usuário logado {string}")
	public void validarUsuariLogado(String user) {

		loginPage.validarUsuarioLogado(driver, wait, user);

	}

}
