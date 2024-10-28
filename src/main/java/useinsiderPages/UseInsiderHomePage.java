package useinsiderPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class UseInsiderHomePage {
    WebDriver driver;
    By Header = By.xpath("//h1");
    By title = By.xpath("//title");
    By company = By.xpath("//*[contains(text(),'Company')]");


    public UseInsiderHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void veryHeader() {
        String getheadertext = driver.findElement(Header).getText();
        assertEquals(getheadertext, "#1 AI-native platform\nfor individualized,\ncross-channel customer experiences");
    }

    public void veryTitle() {
        String getTitleText = driver.findElement(title).getText();
        assertEquals(getTitleText, "#1 Leader in Individualized, Cross-Channel CX — Insider");
    }

}
