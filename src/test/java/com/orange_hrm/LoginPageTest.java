package com.orange_hrm;

import com.orange_hrm.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPageTest extends WebDriverBase {

    @Test(dataProvider = "orangeHRMTestData")
    public void testLoginFunctionality(Map<String, String > data){
        extentTest = extentReports.startTest("testLoginFunctionality");
        extentTest.log(LogStatus.INFO, "Login Process initiated", "Login Process initiated..");
        LoginPage loginPage = new LoginPage(driver, extentTest);
        loginPage.login(data);
        loginPage.verifyLoginSuccessful();
        loginPage.logout();
        loginPage.verifyLogoutSuccessful();
    }
}
