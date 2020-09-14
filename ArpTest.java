package test.network;
import base.BaseTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.network.ArpPage;
import page.MainPage;
import base.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mongodb.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.MainPage;

import com.aventstack.extentreports.*;

import java.io.IOException;


public class ArpTest extends BaseTest {
    private static MainPage mp;
    private static ArpPage dp;

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    final int[] indexes = {1, 3, 5, 9}; // index can be 1-10.

    @BeforeClass
    public void before() {
        mp = new MainPage(getDriver());
        dp = new ArpPage(getDriver());

        htmlReporter = new ExtentHtmlReporter("./MyOwnReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Hostname", "Selenium User");
        extent.setSystemInfo("Enviroment", "QA");
        extent.setSystemInfo("Author", "Selenium Tester");

        htmlReporter.config().setDocumentTitle("Automation Report Demo");
        htmlReporter.config().setReportName("My Own Extent Report");
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @Test(priority = 1, description = "Arp Test")
    public void arpTestStart() throws InterruptedException {


        test = extent.createTest("arpTestStart");
        test.log(Status.INFO, "arpTestStart Test Execution Started!");

        String url = driver.getCurrentUrl();

        test.log(Status.INFO, "URL of WebSite ---> " + url);

        mp.clickNavBarProtocols()
                .clickMenuARP()
                .clickArpStates(indexes)
                .clickSubmitButton();

        test.log(Status.INFO, "arpTestStart Test Execution ENDED!");

    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if( result.getStatus() == ITestResult.FAILURE ){
            String ssPath = GetScreenshotReport.capture(driver, "MyOwnFailScreenShot");
            test.fail(result.getThrowable());
            test.fail("Snapshot below : " + test.addScreenCaptureFromPath(ssPath));
        }
        else{
            test.pass(result.getName() + " | Test Passsed");
        }
    }

    @AfterClass
    public void after() throws InterruptedException {
        Thread.sleep(2000);
      //  mp.reboot();
        if( driver != null ){
            driver.quit();
        }
        extent.flush();
    }
}
