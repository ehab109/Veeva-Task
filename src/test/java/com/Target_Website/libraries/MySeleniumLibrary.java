package com.Target_Website.libraries;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MySeleniumLibrary extends Base {

	final static Logger logger = Logger.getLogger(MySeleniumLibrary.class);

	private WebDriver driver;

	public WebDriver startChromeBrowser() {	// Opening Chrome Driver
		try {

			System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
			// configuring the path to the driver on my my local

			logger.info("starting 'Chrome' browser.");
			driver = new ChromeDriver(); // open the Chrome Browser
			driver.manage().deleteAllCookies(); // Deleting all the website cookies if there's any.

		} catch (Exception e) {  // The Try Catch Block will catch any exception occure
			logger.error("Error: ", e);// Will print out the error in red color so we know 
									   //it's an error
			
			assertTrue(false);// Assert false is to fail the test in case of any exception
		}
		return driver;// method will return the driver value which's Chrome in this scenario
	}

	public void enterText(By by, String inputText) { 
		// This method will take two arguments one is
		// By (which's the element locator and String input text to send any text.
		// Input text is generic so that will allow the user to input any text

		try {
			WebElement element = driver.findElement(by); // We're locating the element on the Webpage
			element.clear(); // Will clear any existing text
			element.sendKeys(inputText); // input and text
		} catch (Exception e) { 
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickButton(By by) {
		// This method will take one arguments 
		// By (which's the element locator, then click it

		try {
			WebElement element = driver.findElement(by); // We're locating the element on the Webpage
			element.click(); // After finding the element by the locator then click it
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void customWait(double inSeconds) {
		// This method will take one arguments which is double
		//and pass it to Thread.sleep argument which is Java Wait

		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void assertionByText(By by, String textName) {
		// This method will take 2 arguments, one is
		// the element locator and second String textName 
		//textName is generic so that will allow the user to input any text
		// in this Scenario we wanted to pass the word "Table" but in other scenarios we might
		// need to pass any other text name for any verification purposes

		try {
			List<WebElement> searchResults = driver.findElements(by);// Finding all the elements 
			// on the search results and store them in a list of webelements.

			
			for (int i = 0; i < searchResults.size(); i++) { // Lopping through every element in each page
				WebElement elem = searchResults.get(i); // Will extract all the Webelement inside of the list

				String actualText = elem.getText();// Extracting the actual text of each webelement
				//and save it in a String Variable
				
				System.out.println(actualText);
				
				if (actualText.contains(textName)) {// Verify by the Text Name if it contains
													// the actual text name.

					System.out.println("This item has 'Fish Oil' word in it");
				} else {
					System.out.println("There is no 'Fish Oil' word in this item");
				}
				
				
				
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void verifyingTextOnTheSearchResults() { // This Method will verify the text on each 
													//individuale items on each search page

		try {
			for (int i = 0; i < 4; i++) { // Lopping through the pages of the website search results 
				// clicking on the arrow button to navigate between pages


				myLib.clickButton(By.xpath("//a[@data-test='next']"));

				myLib.scrollUpDown(5000);

				myLib.customWait(1);// Give a custom wait to move between pages

				// Method call to assert by text as we're looping through the pages
				myLib.assertionByText(By.xpath("//a[@data-test='product-title']"), "Fish Oil");
				// Here i've customized my own xpath to collect all the elements on each search page
                // When i customized my xpath i looked for something mutual between all the webelement 
				//and data-testid attribute and it's value were the common ones
				// // Relative path because it's always better to go for the relative path
				// a was the tag name
				//@ data-testid was the attribute name
				//item description was value of the attribute


			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void scrollUpDown(int pixels) { //This method will use JavascriptExecutor to scroll up and down the UI page
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("scroll(0," + pixels + ")"); // scrolling down
														  // positive pixels for scroll down 
														  // negative pixels for scroll up

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

}
