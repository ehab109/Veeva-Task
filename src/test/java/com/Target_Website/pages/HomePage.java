package com.Target_Website.pages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.Target_Website.libraries.Base;

public class HomePage extends Base {
	final static Logger logger = Logger.getLogger(HomePage.class);
	Actions action = new Actions(driver);
	By searchButton = By.cssSelector("button.SearchInputButton-sc-1opoijs-0.eOzuAz");
	
	public HomePage() {
		
	}
	
	public HomePage searchItem(String inputMerchandise) {
		logger.info("Entering data into the search field");
		myLib.enterText(By.id("search"),inputMerchandise);
		logger.info("Verifying that the searchButton is enabled");
		assertTrue(driver.findElement(searchButton).isEnabled());
		return this;
		
	}
	
	public HomePage verifySuggestedDropDownText() {
		WebElement fishOilItem = driver.findElement(By.xpath("//a[@id='fish oil']"));
		assertTrue(fishOilItem.isDisplayed());
		return this;
	}
	
	
	public SearchResultsPage clickingOnSearchButton() {
		logger.info("Pressing enter key");
		myLib.clickButton(searchButton);
		myLib.customWait(1);
		action.sendKeys(Keys.ENTER).build().perform();
		return new SearchResultsPage();
	}
	
	
}
