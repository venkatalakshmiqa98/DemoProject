package com.orange_hrm.assertions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

public class LoginPageAssertions extends CommonAssertions{

    public static void verifyLogin(ExtentTest extentTest, String expectedText, String actualText) {
        if(expectedText.equals(actualText)){
            extentTest.log(LogStatus.PASS, "Verify Dashboard Title", "Expected:"+expectedText+" --> Actual:"+actualText);
        }else{
            extentTest.log(LogStatus.FAIL, "Verify Dashboard Title", "Expected:"+expectedText+" --> Actual:"+actualText);
        }
    }
}
