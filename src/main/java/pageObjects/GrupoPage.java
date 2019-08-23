package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import util.DriverFactory;
import util.Generica;

public class GrupoPage {

	Generica generica = new Generica();

	@FindBy(how = How.XPATH, using = "/html/body/app-root/app-app-layout/div/app-menu/div/div[2]/div[1]/div/ul/li[7]/ul/li[2]/a/span")
	public WebElement linkGrupos;

	@FindBy(how = How.ID, using = "groupNameInput")
	public WebElement frmNomeGrupo;

	@FindBy(how = How.ID, using = "userSubmitButton")
	public WebElement btnSalvar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"userCancelButton\"]/span")
	public WebElement btnCancelar;

	public void preencherGrupo(String grupo) {
		frmNomeGrupo.sendKeys(grupo);
	}

	public void selecionarConteudo(WebDriver driver, String nomeConteudo, String nomePermissao) {
		WebElement tabelaPermissoes = driver.findElement(By.tagName("p-table"));
		List<WebElement> listaConteudo = driver.findElements(By.tagName("tr"));
		for (WebElement e : listaConteudo) {
			if (e.getText().equals(nomeConteudo)) {
				List<WebElement> perfil = e.findElements(By.tagName("p-radiobutton"));
				if (nomePermissao.equals("Nenhum")) {
					perfil.get(0).click();
				} else if (nomePermissao.equals("Leitura")) {
					perfil.get(1).click();
				} else if (nomePermissao.equals("Leitura e escrita")) {
					perfil.get(2).click();
				}
				break;
			}
		}
		
	
		
	}	
	public void validarMessage(String msg) {
		assertEquals(msg, generica.recuperarMensagemTitle(DriverFactory.driver));
	}
    			
    	  }
    	  


