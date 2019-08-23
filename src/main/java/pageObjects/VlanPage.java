package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.DriverFactory;
import util.Generica;
import util.Menu;

public class VlanPage {

	Generica generica = new Generica();
	WebDriverWait wait = null;
	Menu menu = new Menu();

	@FindBy(how = How.XPATH, using = "//span[text()='Adicionar VLAN']")
	public WebElement adicionarVlan;

	@FindBy(how = How.NAME, using = "typeService")
	public WebElement selectTpService;

	@FindBy(how = How.NAME, using = "VlanName")
	public WebElement inputNameVlan;

	@FindBy(how = How.NAME, using = "VlanVID")
	public WebElement inputVlanId;

	@FindBy(how = How.NAME, using = "VlanDescription")
	public WebElement inputDescricao;

	@FindBy(how = How.XPATH, using = "//span[text()='Salvar']")
	public WebElement button;

	public void clicarButtonAdicionar() {
		DriverFactory.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		adicionarVlan.click();

	}

	public void selecionarTipoService(String tipoServico) {

		DriverFactory.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		selectTpService.click();
		generica.selecionarComboBox(selectTpService, tipoServico);

	}

	public void informarNomeVlan(String vlan) {
		DriverFactory.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		inputNameVlan.clear();
		inputNameVlan.sendKeys(vlan);

	}

	public void informarVlanId(String id) {
		inputVlanId.clear();
		inputVlanId.sendKeys(id);
	}

	public void informarCampoObservacao(String observacao) {
		inputDescricao.clear();
		inputDescricao.sendKeys(observacao);

	}

	public void clicarSalvar() {
		button.click();
	}

	public void validarMensagem(WebDriver driver, String mensagem) {
		DriverFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		assertEquals(mensagem, generica.recuperarMensagemTitle(driver));
	}

	public void visualizarListagemVlan() {

		
	}

}