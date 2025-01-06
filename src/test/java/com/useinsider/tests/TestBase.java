package com.useinsider.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.useinsider.pages.BrowseOpenPositionPage;
import com.useinsider.pages.CareersPage;
import com.useinsider.pages.HomePage;
import com.useinsider.pages.QualityAssurancePage;
import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.ConfigurationReader;
import com.useinsider.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;


public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected ExtentReports report;
    protected ExtentSparkReporter sparkReporter;
    protected ExtentTest extentTest;

    protected HomePage homePage;
    protected CareersPage careersPage;
    protected QualityAssurancePage qualityAssurancePage;
    protected BrowseOpenPositionPage browseOpenPositionPage;

    protected  String  url=ConfigurationReader.get("url");
    protected  String  careersUrl= url+ ConfigurationReader.get("careers");
    protected  String  careersQAUrl= careersUrl + ConfigurationReader.get("careersQA");

   protected String expectedLocation;
   protected String expectedDepartment;
   protected String expectedPosition;


    @BeforeTest
    public void setUpReport(){

        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"/test-output/report.html";

        report = new ExtentReports();
        sparkReporter=new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Insider Test Reports");
        sparkReporter.config().setDocumentTitle("Test Reports");
        report.attachReporter(sparkReporter);
        report.setSystemInfo("Environment",ConfigurationReader.get("url"));
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));
        report.setSystemInfo("Test Engineer","Fatih Tosun");
    }
    @AfterTest
    public void tearDownReport(){
        report.flush();
    }

    @BeforeMethod
    public void setUp() {
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage=new HomePage();
        careersPage=new CareersPage();
        qualityAssurancePage=new QualityAssurancePage();
        browseOpenPositionPage=new BrowseOpenPositionPage();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE){
            extentTest.fail(result.getName());
            String screenshotPath = BrowserUtils.getScreenshot(result.getName());
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.fail(result.getThrowable());
        }
        BrowserUtils.waitFor(4);
        Driver.closeDriver();
    }
}
