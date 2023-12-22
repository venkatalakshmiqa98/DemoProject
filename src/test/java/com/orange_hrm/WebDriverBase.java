package com.orange_hrm;

import com.orange_hrm.util.ExcelReader;
import com.orange_hrm.util.PropertyFileReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class WebDriverBase {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest(alwaysRun = true)
    //@Parameters({"browserName"})
    public void setUp() throws IOException {

        extentReports = new ExtentReports(System.getProperty("user.dir")+"\\extent-reports\\report.html");

        PropertyFileReader.readPropertiesFile("src/main/resources/application_qa.properties");
        String browserName = PropertyFileReader.getProperty("browser.name");
        switch (browserName){
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "FF":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
                driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

    @DataProvider(name = "orangeHRMTestData")
    public Iterator<Object[]> stackOverflowDataProvider(Method method) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\" + PropertyFileReader.getProperty("filename");
        Iterator<Object[]> iterator = ExcelReader.excelReader(filePath, method.getName());
        return iterator;
    }

}