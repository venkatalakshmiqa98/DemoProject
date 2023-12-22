package com.orange_hrm.pages;

import com.orange_hrm.assertions.LoginPageAssertions;
import com.orange_hrm.util.PropertyFileReader;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class LoginPage extends Page{

    public LoginPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='username']")
    private WebElement userName;

    @FindBy(css = "input[name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text() = ' Login ']")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='oxd-topbar-header-title']")
    private WebElement dashBoardPageTitle;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement userProfile;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginHeader;

    public void login(Map<String, String > data){
        launchUrl(PropertyFileReader.getProperty("app.url"));
        sendKeys(userName, data.get("Username"), "");
        sendKeys(password, data.get("Password"), "");
        click(loginBtn, "LoginButton");
    }

    public void verifyLoginSuccessful() {
        waitForElementVisible(dashBoardPageTitle, "Dashboard");
        LoginPageAssertions.verifyLogin(extentTest, "Dashboard", dashBoardPageTitle.getText());
    }

    public void logout() {
        click(userProfile, "Profile Link");
        click(logoutLink, "Logout Button");
    }

    public void verifyLogoutSuccessful() {
        waitForElementVisible(loginHeader, "Dashboard");
        LoginPageAssertions.verifyLogin(extentTest, "Login", loginHeader.getText());
    }
}
