package config;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import java.io.File;

public class Config {

    public static boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void takeSnapShot(WebDriver driver, ITestResult result) throws Exception {
        // To create reference of TakesScreenshot
        TakesScreenshot screenshot=(TakesScreenshot)driver;
        // Call method to capture screenshot
        File src=screenshot.getScreenshotAs(OutputType.FILE);
        // Copy files to specific location
        // result.getName() will return name of test case so that screenshot name will be same as test case name
        FileUtils.copyFile(src, new File(System.getProperty("user.dir")+result.getName()+".png"));
        System.out.println("Successfully captured a screenshot in " + System.getProperty("user.dir"));
    }
}
