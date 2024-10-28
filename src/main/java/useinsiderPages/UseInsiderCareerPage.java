package useinsiderPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UseInsiderCareerPage {
    WebDriver driver;
    By Header = By.xpath("//h1");
    By title = By.xpath("//title");
    By company = By.xpath("//*[contains(text(),'Company')]");
    By career = By.xpath("//*[contains(text(),'Career')]");
    By location = By.xpath("//*[@id='career-our-location']");


    public UseInsiderCareerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void veryHeader() {
        String getheadertext = driver.findElement(Header).getText();
        assertEquals(getheadertext, "Ready to disrupt?");
    }

    public void veryTitle() {
        String getTitleText = driver.findElement(title).getText();
        assertEquals(getTitleText, "Ready to disrupt? | Insider Careers");
    }

    public void clickCareer() {
        driver.findElement(company).click();
        driver.findElement(career).click();
    }

    public void veryUrl() {
        String location = driver.getCurrentUrl();
        assertEquals(location, "https://useinsider.com/careers/");
    }

    public void veryLocations() {
        assertTrue(driver.findElement(location).isDisplayed());
    }
}
