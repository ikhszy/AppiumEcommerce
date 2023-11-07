package org.appiumFramework.ecommerce.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReporterObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "PASS");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
