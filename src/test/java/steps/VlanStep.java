package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.LoginPage;
import pageObjects.VlanPage;
import setup.RecuperarSenha;
import setup.SetupJDBC;
import util.DriverFactory;
import util.Generica;
import util.Menu;
import util.PropertyReader;

public class VlanStep {

	DriverFactory factory = new DriverFactory();
	LoginPage loginPage = new LoginPage();
	Menu menuPage = new Menu();
	Generica generica = new Generica();
	SetupJDBC jdbc = new SetupJDBC();
	VlanPage vlanPage = new VlanPage();

	static WebDriver driver;

	public VlanStep() throws Exception {
		driver = DriverFactory.driver;
		if (driver == null) {
			driver = factory.openBrowser();
		}

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		menuPage = PageFactory.initElements(driver, Menu.class);
		vlanPage = PageFactory.initElements(driver, VlanPage.class);
	}

	@Then("visualizo a tela listagem de vlans")
	public void visualizarTelaVlans() {

		vlanPage.visualizarListagemVlan();
	}

	@Given("seleciono o botao Adicionar Vlans")
	public void adicionarVlan() {

		vlanPage.clicarButtonAdicionar();

	}

	@Given("seleciono o tipo de serviço {string}")
	public void selecionaTipoServico(String tipoServico) {

		vlanPage.selecionarTipoService(tipoServico);
	}

	@Given("informo nome vlan {string}")
	public void informarNomeVlan(String nomeVlan) {

		vlanPage.informarNomeVlan(nomeVlan);
	}

	@Given("informo VLAN ID {string}")
	public void vlanId(String vlanId) {

		vlanPage.informarVlanId(vlanId);

	}

	@Given("informo campo observacao {string}")
	public void informarObservacao(String observacao) {

		vlanPage.informarCampoObservacao(observacao);

	}

	@Given("seleciono o botao salvar")
	public void clicarButtonSalvar() {

		vlanPage.clicarSalvar();

	}
	
	@Given("informo uma vlan com o nome repetido {string}")
	public void informarVlanDuplicado(String nomeVlan) throws ClassNotFoundException {
		jdbc.executaQueryCascate(new PropertyReader().readProperty("db.planrepository"), 
				new PropertyReader().readProperty("sql.vlan.insert"));
		
		vlanPage.informarNomeVlan(nomeVlan);
	
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
