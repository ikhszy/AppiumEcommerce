package org.appiumFramework.ecommerce.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\index.html");
		reporter.config().setDocumentTitle("eCommerce Appium Android");
		reporter.config().setReportName("eCommerce Appium Android");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "Ikhsan");
		return extent;
	}

}
