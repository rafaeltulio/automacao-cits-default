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

public class UsuarioPage {

	Generica generica = new Generica();
	WebDriverWait wait = null;
	Menu menu = new Menu();

	@FindBy(how = How.ID, using = "username")
	public WebElement inputUserName;

	@FindBy(how = How.ID, using = "password")
	public WebElement inputPassword;

	@FindBy(how = How.ID, using = "kc-login")
	public WebElement buttonLogin;

	@FindBy(how = How.XPATH, using = "//span[text()='Adicionar Usuário']")
	public WebElement adicionarUsuario;

	@FindBy(how = How.ID, using = "userFirstNameInput")
	public WebElement userName;

	@FindBy(how = How.ID, using = "userLastNameInput")
	public WebElement sobreNomeUser;

	@FindBy(how = How.ID, using = "email")
	public WebElement inputEmail;

	@FindBy(how = How.ID, using = "userUsernameInput")
	public WebElement inputLogin;

	@FindBy(how = How.NAME, using = "groups")
	public WebElement selectGroup;

	@FindBy(how = How.ID, using = "userPasswordInput")
	public WebElement inputSenha;

	@FindBy(how = How.ID, using = "userConfirmPasswordInput")
	public WebElement inputConfirmSenha;

	@FindBy(how = How.NAME, using = "Temporary")
	public WebElement checkRedefinirPrimeiroAcesso;

	@FindBy(how = How.ID, using = "userSubmitButton")
	public WebElement botaoSalvar;

	public void preencherLogin(String user, String password) {

		inputUserName.clear();
		inputUserName.sendKeys(user);

		inputPassword.clear();
		inputPassword.sendKeys(password);

		buttonLogin.click();

	}

	public void adicionarUsuario() {
		adicionarUsuario.click();

	}

	public void enterUser(String nome) {
		userName.clear();
		userName.sendKeys(nome);

	}

	public void enterLastName(String sobreNome) {
		sobreNomeUser.clear();
		sobreNomeUser.sendKeys(sobreNome);
	}

	public void informarEmail(String email) {
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void informarLogin(String login) {
		inputLogin.clear();
		inputLogin.sendKeys(login);
	}

	public void selectGroup(String group) {
		selectGroup.click();
		generica.selecionarComboBox(selectGroup, group);
	}

	public void enterSenha(String senha, String confirmaSenha) {

		inputSenha.clear();
		inputSenha.sendKeys(senha);

		inputConfirmSenha.clear();
		inputConfirmSenha.sendKeys(confirmaSenha);
	}

	public void clicarButtonSalvar() {

		botaoSalvar.click();

	}

	public void validarMensagem(WebDriver driver, String mensagem) {
		DriverFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		assertEquals(mensagem, generica.recuperarMensagemTitle(driver));
	}

	public void validarMensagemCampos(WebDriver driver, String mensagemCampos) {
		assertEquals(mensagemCampos, generica.recuperaMensagemCampos(driver));
	}

}
