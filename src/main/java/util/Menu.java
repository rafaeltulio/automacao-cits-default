package util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Menu {

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'nano-content sidebar-scroll-content')]")
	private WebElement menuPrincipalUsuario;

	@FindBy(how = How.XPATH, using = "//a[@class='menu-btn']")
	private WebElement btnBaixaResolucao;

	@FindBy(how = How.XPATH, using = "/html/body/app-root/app-app-layout/div/app-menu/div/div[2]")
	private WebElement menuBaixaResolucao;


	/**
	 * Método para verificar se a resolução está acima ou abaixo de 1280
	 */
	public void verificaResolucaoMenu() {
		if (btnBaixaResolucao.isDisplayed()) {
			btnBaixaResolucao.click();
		}
	}

	/**
	 * Método para clicar no menu
	 * 
	 * @param tag
	 * @param nomeMenu
	 */
	public void selecionarMenuVertical(String tag, String nomeMenu) {
		DriverFactory.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> listaMenus = menuPrincipalUsuario.findElements(By.tagName(tag));
		for (WebElement w : listaMenus) {
			if (!w.getText().isEmpty()) {
				if (w.getText().equals(nomeMenu)) {
					w.click();
				}
			}
		}
	}
}
