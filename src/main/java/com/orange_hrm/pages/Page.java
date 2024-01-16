package com.orange_hrm.pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected ExtentTest extentTest;

    public Page(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 30);
        this.extentTest = extentTest;
    }

    public void launchUrl(String url){
        driver.get(url);
    }

    public void click(WebElement webElement, String logMessage) {
        try {
            waitForElementVisible(webElement, logMessage);
            extentTest.log(LogStatus.INFO, "Clicking on Element ", logMessage);
            webElement.click();
            extentTest.log(LogStatus.PASS, "Clicking on Element is Success ", logMessage);
        } catch (Exception exception) {
            extentTest.log(LogStatus.FAIL, "Clicking on Element is Failed ", exception.getMessage());
        }
    }
    public void sendKeys(WebElement webElement, String value, String logMessage) {
        try {
            waitForElementVisible(webElement, logMessage);
            extentTest.log(LogStatus.INFO, "Entering the text :", value);
            webElement.sendKeys(value);
            extentTest.log(LogStatus.PASS, "Entering the is successfull:", value);
        } catch (Exception exception) {
            extentTest.log(LogStatus.FAIL, "Entering the text is Failed ", exception.getMessage());
        }
    }

    protected void clear(WebElement webElement, String logMessage) {
        try {
            waitForElementVisible(webElement, logMessage);
            webElement.clear();
        } catch (Exception exception) {
        }
    }

    public void waitForElementVisible(WebElement webElement, String logMessage){
        //extentTest.log(LogStatus.INFO, "Waiting for Element visible", logMessage);
        try{
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        }catch (Exception e){
            extentTest.log(LogStatus.FAIL, "Waiting for Element Failed", e.getMessage());
        }
    }

}
