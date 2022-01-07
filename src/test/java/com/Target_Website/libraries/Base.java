package com.Target_Website.libraries;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

public class Base {

	final static Logger logger = Logger.getLogger(Base.class); // Initializing the logger as a Login
	//Mechanism which will help to describe the breakdown of the step by step process.


	public static MySeleniumLibrary myLib; // I've created an object of my Library class
	public static WebDriver driver; // Creating a Static driver object
	
	// Using the TestNG Annotations for SetUp, Closing the browser and clean up
	@BeforeMethod   // @BeforeMethod will be executed just before any Function or Method with @test 
					//Annotation starts

	public void setUp() {
		
			myLib = new MySeleniumLibrary();
			driver = myLib.startChromeBrowser(); // Method call from the library to start Chrome driver
			
			driver.get("https://www.target.com"); // Navigating to the URL
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// Setting up the implicit
			// wait for 10 seconds (0 by default) to tell the driver to wait for 10 seconds before it
			// throws "No such Element Exception"
			
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			// Setting up Page load time 
			 // to 10 seconds before it throws "Time out Exception"
			
			driver.manage().window().maximize(); // Maximizing the Window
			logger.info("Maxmizing the Window");
			
			String websiteTitle = driver.getTitle();// This line of code will get us the Actual page title.
			// then save it in a string variable, so we can use it to verify if the Actual and expected title
			// are matching
			logger.info("website title is: " + websiteTitle);
			String expectedTitle = "Target : Expect More. Pay Less."; //Expected title
			Assert.assertEquals(websiteTitle, expectedTitle); // Verifying the page title
			logger.info("Searching for Fish Oil");
	}

	@AfterMethod	//@AfterMethod will be executed just after any Function or Method with @test 
					//Annotation ends

	public void closingBrowsers() {
		try {
			logger.info("After Method....");
			myLib.customWait(5); // Waiting for 5 seconds before the driver closes.

			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} finally {
			if (driver != null) {
				driver.close();// if the driver NOT equal to null which means we have an open browser
							   // close that browser.
			}
		}

	}

	@AfterClass    //@Afterclass will be executed just after will be run after all the test methods 
				   //in the current class have been executed.

	public void cleanUpProcess() {
	
			logger.info("After Class....");

			if (driver != null) {
				driver.quit();	// if the driver NOT equal to null which means we have other browsers 
								// are opening in the background by selenium 
								// quit that browser.
			}
	}
}
