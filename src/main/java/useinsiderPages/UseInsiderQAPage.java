package useinsiderPages;

import config.Config;
import io.eotsevych.select2.Select2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class UseInsiderQAPage {
    WebDriver driver;
    By seeAllQa = By.xpath("//*[contains(text(),'See all QA jobs')]");
    By jobList = By.xpath("//*[@id='jobs-list']");
    By jobIstanbul = By
            .xpath("//*[contains(@class, 'position-list-item col-12 col-lg-4 qualityassurance istanbul-turkey full-timeremote')]");
    By viewRoleBtn = By.cssSelector("a.btn.btn-navy.rounded");
    By title = By.xpath("//title");
    By department = By.xpath("//*[@id='select2-filter-by-department-container']");
    By selector = By.xpath("//*[@id='filter-by-location']");

    public UseInsiderQAPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openPage() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    public void clickSeeAllQAJobs() {
        driver.findElement(seeAllQa).click();
    }


    public void select2() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .presenceOfElementLocated(department));

        WebElement element = driver.findElement(selector);

        Select2 select = new Select2(element);
        select.selectByText("Istanbul, Turkey");
    }

    public void veryJobList() {
        assertFalse(Config.isElementPresent(jobList, driver));
    }

    public void veryJobListPositive() {
        WebElement iframe = driver.findElement(By.id("career-position-filter"));
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, 500)
                .perform();


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .presenceOfElementLocated(jobIstanbul)).isDisplayed();
    }

    public void clickViewRoleBtn() {
        WebElement roleBtn = driver.findElement(jobIstanbul).findElement(viewRoleBtn);
        roleBtn.click();
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .presenceOfElementLocated(title));
        assertEquals(driver.getTitle(), "Insider. - Senior Software Quality Assurance Engineer");
        assertEquals(driver.getCurrentUrl(), "https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc");
    }

}
