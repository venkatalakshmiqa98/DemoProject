package com.orange_hrm.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    public ExtentManager(){
        extentReports = extentReports = new ExtentReports(System.getProperty("user.dir")+"\\extent-reports\\report.html");
    }
}
