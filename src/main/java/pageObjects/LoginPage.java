package pageObjects;

import static org.junit.Assert.assertEquals;

import java.awt.Menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	Menu menu = new Menu();
	@FindBy(how = How.ID, using = "username")
	public WebElement inputUserName;
	
	@FindBy(how = How.ID, using = "password")
	public WebElement inputPassword;
	
	@FindBy(how = How.ID, using = "kc-login")
	public WebElement buttonLogin;
	
	@FindBy(how = How.XPATH, using = "//span[@class='kc-feedback-text']")
	public WebElement mensagemErro;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'topbar-item-name profile-name ')]")
	public WebElement userLogado;
	
	public void preencherLogin(String usuario, String senha) {
		inputUserName.clear();
		inputUserName.sendKeys(usuario);
		
		inputPassword.clear();
		inputPassword.sendKeys(senha);
		
		buttonLogin.click();
		
	}

	public void capturarMensagemRetorno(String mensagemRetorno) {

		assertEquals(mensagemRetorno, mensagemErro.getText());
		
	}

	public void validarUsuarioLogado(WebDriver driver, WebDriverWait wait, String user) {

		assertEquals(user, userLogado.getText());
	}

}
