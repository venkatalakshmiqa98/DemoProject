package com.orange_hrm.pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends Page{

    public DashboardPage(WebDriver driver, ExtentTest extentTest) {
        super(driver, extentTest);
        PageFactory.initElements(driver, this);
    }

}
