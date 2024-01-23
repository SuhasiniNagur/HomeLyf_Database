package com.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.android.AndroidDriver;


public class TestListeners implements ITestListener{
	

	AndroidDriver driver;
	ExtentSparkReporter spark;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureReport() {
		spark = new ExtentSparkReporter("Reports//ExtentListenerReport.html");
		reports = new ExtentReports();
		reports.attachReporter(spark);
		
		reports.setSystemInfo("Version:", "v1.1");
		reports.setSystemInfo("Environment", "OA");
		reports.setSystemInfo("Device Name", "Pixel3");
		reports.setSystemInfo("User name :", "Suhasini");
		

		spark.config().setReportName("Extent Listener Reports");
		spark.config().setDocumentTitle("Demo report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReporter(spark);
		spark.config().setEncoding("utf-8");
	}

	public void onStart(ITestContext result) {		
		 
		configureReport();
		System.out.println("onstart");
	 }
	
	public void onFinish(ITestContext result) {
		System.out.println("onfinish");
		reports.flush();
	}
	
	public void onTestFailure(ITestResult result) {
		

		test = reports.createTest(result.getName());
		 
		  
		  String screenshotPath=System.getProperty("user.dir")+"//screenshot/"+result.getName()+".png";
					
		   test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
		
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the Failed Testcase is : "+result.getName(), ExtentColor.RED));
		  }
	
	
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped Testcase : "+result.getName(), ExtentColor.YELLOW));
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("Name of started Method"+result.getMethod());
	}
	
	public void onTestSuccess(ITestResult result) {

		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Passed Testcase : "+result.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(" onTestFailedButWithinSuccessPercentage"+result.getName());
	}
	
	
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/screenshot/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}