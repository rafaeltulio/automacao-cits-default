package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.SetupJDBC;
import util.DriverFactory;
import util.Generica;
import util.Menu;
import util.PropertyReader;

public class TipoServicoPage {
	
	WebDriverWait wait = null;
	Menu menu = new Menu();
	Generica generica = new Generica();
	SetupJDBC jdbc = new SetupJDBC();
	
	
	@FindBy(how = How.XPATH, using = "//h2")
	public WebElement titulo;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Adicionar Tipo de Serviço']")
	public WebElement buttonAdicionar;
	
	@FindBy(how = How.NAME, using = "typeserviceDescription")
	public WebElement nameTipoServico;
	
	@FindBy(how = How.XPATH, using = "//p-spinner[@name='typeserviceDsMax']")
	public WebElement inputDownstream;
	
	@FindBy(how = How.NAME, using = "typeserviceUsMin")
	public WebElement inputAssured;
	

	@FindBy(how = How.NAME, using = "typeserviceUsMin")
	public WebElement inputUpstream;
	
	@FindBy(how = How.ID, using = "userSubmitButton")
	public WebElement botaoSalvar;
	
	public void validarTituloTela(String tituloTela) {
		DriverFactory.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertEquals(tituloTela, titulo.getText());
	}

	public void clicarButtonAdicionar() {
		buttonAdicionar.click();
		
	}

	public void preencherTipoServico(String nomeTpServico) {
		nameTipoServico.clear();
		nameTipoServico.sendKeys(nomeTpServico);
		
	}

	public void preencherDownstream(String downstreamMax) {
		inputDownstream.click();
		WebElement inputDownstramMax = inputDownstream.findElement(By.name("typeserviceDsMax"));
		inputDownstramMax.clear();
		inputDownstramMax.sendKeys(downstreamMax);
	}

	public void preencherAssured(String assured) {

		System.out.println("Implementar");
	}

	public void preencherUpstream(String upstream) {

		System.out.println("Implementar");
	}

	public void preencherCos(String cos) {

		System.out.println("Implementar");
	}

	public void selecionaDscp(String dscp) {
		// TODO Auto-generated method stub
		System.out.println("Implementar");
		
	}

	public void clicarButtonSalvar() {

		botaoSalvar.click();
	
	}

	public void validarNomeDuplicado(String nomeTpServico) throws ClassNotFoundException {
		jdbc.executaQueryCascate(new PropertyReader().readProperty("db.planrepository"), 
				new PropertyReader().readProperty("sql.type_service.insert.massa"));
		
		nameTipoServico.clear();
		nameTipoServico.sendKeys(nomeTpServico);
	
	
	}

}
