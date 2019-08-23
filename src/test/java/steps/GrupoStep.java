package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.GrupoPage;
import pageObjects.LoginPage;
import util.DriverFactory;
import util.Generica;
import util.Menu;

public class GrupoStep {

	DriverFactory factor = new DriverFactory();
	LoginPage loginPage;
	Generica generica = new Generica();
	GrupoPage grupoPage = new GrupoPage();
	Menu menu = new Menu();
	WebDriver driver;

	public GrupoStep() throws Exception {
		driver = DriverFactory.driver;
		if (driver.equals(null)) {
			driver = factor.openBrowser();
		}
		loginPage = PageFactory.initElements(factor.openBrowser(), LoginPage.class);
		menu = PageFactory.initElements(driver, Menu.class);
		grupoPage = PageFactory.initElements(factor.openBrowser(), GrupoPage.class);
	}

	@Given("seleciono o menu {string} ")
	public void acessaMenuSubmenu(String nomeMenu) throws InterruptedException {
		menu.selecionarMenuVertical("span", nomeMenu);
	}

	@When("seleciono o sub menu {string}")
	public void acessaSubMenu(String nomeSubMenu) throws InterruptedException {
		menu.selecionarMenuVertical("span", nomeSubMenu);
	}

	@Then("preenche o nome do grupo {string}")
	public void preenche_o_nome_do_grupo(String nomeGrupo) {
		grupoPage.preencherGrupo(nomeGrupo);
	}

	@Then("seleciona a lista de conteúdo: {string} e suas permissões {string}")
	public void seleciona_a_lista_de_conteúdo_e_suas_permissões(String nomeConteudo, String nomePermissao) {
		grupoPage.selecionarConteudo(driver, nomeConteudo, nomePermissao);
	}

	@Then("clica no botao {string}")
	public void clica_no_botao_salvar(String btn) throws InterruptedException {
		generica.rolarScroll(driver);
		generica.clicarButton(driver, btn);
	}

	@Then("o sistema valida as informacoes e exibe a mensagem {string}")
	public void o_sistema_valida_as_informacoes_e_exibe_a_mensagem(String mensagemRetorno) {
		grupoPage.validarMessage(mensagemRetorno);
	}
}