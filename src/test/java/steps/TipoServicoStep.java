package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.LoginPage;
import pageObjects.TipoServicoPage;
import util.DriverFactory;
import util.Generica;
import util.Menu;

public class TipoServicoStep {

	LoginPage loginPage = new LoginPage();
	Menu menuPage = new Menu();
	TipoServicoPage tpService = new TipoServicoPage();
	Generica generica = new Generica();
	DriverFactory factory = new DriverFactory();

	static WebDriver driver;

	public TipoServicoStep() throws Exception {
		driver = DriverFactory.driver;
		if (driver == null) {
			driver = factory.openBrowser();
		}

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		menuPage = PageFactory.initElements(driver, Menu.class);
		tpService = PageFactory.initElements(driver, TipoServicoPage.class);
	}

	@Then("visualizo a tela listagem de {string}")
	public void visualizarTela(String tituloTela) {
		
		tpService.validarTituloTela(tituloTela);

	}

	@Given("clicar no botão adicionar tipo serviço")
	public void clicarAdicionarServico() {
		
		tpService.clicarButtonAdicionar();

	}

	@Given("informar nome do serviço {string}")
	public void informarNomeServico(String nomeTpServico) {
		
		tpService.preencherTipoServico(nomeTpServico);

	}

	@Given("informar downstream máximo {string}")
	public void informarDownstream(String downstreamMax) {
		
		tpService.preencherDownstream(downstreamMax);

	}

	@Given("informar assured upstream {string}")
	public void informarAssuredUpstream(String assured) {
		
		tpService.preencherAssured(assured);

	}

	@Given("informar upstream máximo {string}")
	public void informarUpstream(String upstream) {
		
		tpService.preencherUpstream(upstream);
	}

	@Given("selecionar cos {string}")
	public void selecionarCos(String cos) {
		
		tpService.preencherCos(cos);
	}

	@Given("selecionar dscp {string}")
	public void selecionarDscp(String dscp) {
		
		tpService.selecionaDscp(dscp);
	}

	@Given("clicar no botão salvar tipo servico")
	public void botaoSalvar() {
		
		tpService.clicarButtonSalvar();
	}

	
	
	@Given("informar nome do serviço duplicado {string}")
	public void informarServicoDuplicado(String nomeTpServico) throws ClassNotFoundException {
		
		tpService.validarNomeDuplicado(nomeTpServico);
	}

}
