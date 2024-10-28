package setUp;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import useinsiderPages.UseInsiderCareerPage;
import useinsiderPages.UseInsiderHomePage;
import useinsiderPages.UseInsiderQAPage;

import java.time.Duration;


public class SetUp {
    WebDriver driver;
    UseInsiderHomePage objUseInsiderHomePage;
    UseInsiderCareerPage objUseInsiderCareerPage;
    UseInsiderQAPage objUseInsiderQAPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new Exception("Incorrect Browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://useinsider.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(text(),'Accept All')]")).click();
    }

    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) {
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Config.takeSnapShot(driver, result);
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }

    @Test(priority = 1)
    public void navigate_to_homepage() {
        objUseInsiderHomePage = new UseInsiderHomePage(driver);
        objUseInsiderHomePage.veryHeader();
        objUseInsiderHomePage.veryTitle();
    }

    @Test(priority = 2)
    public void navigate_to_career() {
        objUseInsiderCareerPage = new UseInsiderCareerPage(driver);
        objUseInsiderCareerPage.clickCareer();
        objUseInsiderCareerPage.veryHeader();
        objUseInsiderCareerPage.veryTitle();
        objUseInsiderCareerPage.veryUrl();
        objUseInsiderCareerPage.veryLocations();
    }

    //negative scenario cause jobs list is not displayed unless you scroll
    @Test
    public void navigate_to_qa() throws InterruptedException {
        objUseInsiderQAPage = new UseInsiderQAPage(driver);
        objUseInsiderQAPage.openPage();
        objUseInsiderQAPage.clickSeeAllQAJobs();
        objUseInsiderQAPage.select2();
        objUseInsiderQAPage.veryJobList();
    }

    //positive case to check job list
    @Test
    public void check_presence_of_job_list() throws InterruptedException {
        objUseInsiderQAPage = new UseInsiderQAPage(driver);
        objUseInsiderQAPage.openPage();
        objUseInsiderQAPage.clickSeeAllQAJobs();
        objUseInsiderQAPage.select2();
        objUseInsiderQAPage.veryJobListPositive();
    }

    @Test
    public void check_lever_app_form_page_redirect() throws Exception {
        objUseInsiderQAPage = new UseInsiderQAPage(driver);
        objUseInsiderQAPage.openPage();
        objUseInsiderQAPage.clickSeeAllQAJobs();
        objUseInsiderQAPage.select2();
        objUseInsiderQAPage.veryJobListPositive();
        objUseInsiderQAPage.clickViewRoleBtn();
    }
}
