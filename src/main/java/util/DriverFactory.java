package util;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.github.bogdanlivadariu.gifwebdriver.GifWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import setup.SetupJDBC;

public class DriverFactory extends RunListener {

	public static WebDriver driver = null;
	GifWebDriver gifWebDriver;
	DesiredCapabilities capabilities = null;
	SetupJDBC jdbc = new SetupJDBC();
	Generica generica = new Generica();
	static Logger logger = Logger.getLogger(DriverFactory.class.toString());

	/**
	 * Método para retornar a instância do webdriver
	 * 
	 * @throws Exception
	 * 
	 */
	public WebDriver openBrowser() throws Exception {
		System.setProperty("webdriver.chrome.verboseLogging", "false");
		if (driver != null) {
			return driver;
		}

		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			capabilities = DesiredCapabilities.internetExplorer();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			capabilities = DesiredCapabilities.firefox();
		}
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--browser-test");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		}

		// driver = new RemoteWebDriver(new URL(new
		// PropertyReader().readProperty("host.execucao")), capabilities);
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (driver == null) {
			throw new Exception("Browser inválido: " + browser);
		}
		return driver;
	}

	/**
	 * Método que executa a finalização da execução dos testes
	 * 
	 * @param scenario
	 * @throws ClassNotFoundException
	 */
	@After
	public void embedScreenshot(Scenario scenario) throws Exception {
		if (scenario != null) {
			if (scenario.isFailed()) {
				try {

					WebDriver augmentedDriver = new Augmenter().augment(driver);
					byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenshot, "image/png");
					scenario.write("URL at failure: " + driver.getCurrentUrl());

					WebDriver augmentedDriver1 = new Augmenter().augment(driver);
					File screenshot1 = ((TakesScreenshot) augmentedDriver1).getScreenshotAs(OutputType.FILE);
				} catch (WebDriverException wde) {
					System.out.println("Processo de recuperação do print do cenário falhou: " + wde.getMessage());
					scenario.write("Processo de recuperação do print do cenário falhou: " + wde.getMessage());
				} catch (ClassCastException cce) {
					System.out.println("Não foi possível gerar o arquivo: " + cce);
					cce.printStackTrace();
				}
				logger.info(
						"------------------------------------------ Cenário: " + scenario.getName() + ": REPROVADO");
			} else {
				logger.info("------------------------------------------ Cenário: " + scenario.getName() + ": APROVADO");
			}
			this.executeSetupAfter(scenario);
		}
		if (driver != null) {
			driver.quit();
			driver = null;
		}

		logger.info("Teste finalizado......");
	}

	/**
	 * Método para inserir dados na base para preparação dos testes
	 * 
	 * @param scenario
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	@Before
	public void executeSetupBefore(Scenario scenario) throws ClassNotFoundException, InterruptedException {
		logger.info("------------------------------------------ Before: Iniciando preparação do setup");
		logger.info("------------------------------------------ Before: Finalizando preparação do setup");

	}

	/**
	 * Método para limpar a base de dados após a execução dos testes
	 * 
	 * @param scenario
	 * @throws ClassNotFoundException
	 */
	public void executeSetupAfter(Scenario scenario) throws ClassNotFoundException {
		logger.info("------------------------------------------ After: Iniciando limpeza do setup");
		for (String s : scenario.getSourceTagNames()) {
			String tag = s.replace("@", "");
			if (driver != null) {
				driver.quit();
				driver = null;
			}
			if (tag.equals(Tags.Usuario.toString())) {
				logger.info("Manter Usuário: sendo deletados da base.");
				jdbc.executaQueryCascate(new PropertyReader().readProperty("db.planrepository"),
						new PropertyReader().readProperty("sql.usuario.cadastro.delete"));
			}
			if (tag.equals(Tags.Vlan.toString())) {
				logger.info("Manter Vlans: sendo deletados da base.");
				jdbc.executaQueryCascate(new PropertyReader().readProperty("db.planrepository"),
						new PropertyReader().readProperty("sql.vlan.delet"));
			}
		}

	}
}