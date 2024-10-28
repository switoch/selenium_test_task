import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) {
        String url = "https://useinsider.com/";
        String expectedTitle0 = "#1 Leader in Individualized, Cross-Channel CX — Insider";
        String expectedTitle1 = "Ready to disrupt? | Insider Careers";
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle0)){
            System.out.println("Web page is opened");
        }
        else{
            System.out.println("Web page could not open.");
        }
        driver.findElement(By.xpath("//*[contains(text(),'Company')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Career')]")).click();
        if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle1)){
            System.out.println("Web page is opened");
        }
        else{
            System.out.println("Web page could not open.");
        }
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions
//                        .elementToBeClickable(By.xpath("//*[contains(text(),'Company')]")))
//                .click();
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions
//                        .elementToBeClickable(By.xpath("//*[contains(text(),'Career')]")))
//                .click();

           // driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul[1]/li[6]/div/div[2]/a[2]")).click();
        //driver.close();
    }
}
