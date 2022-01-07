package com.Target_Website.pages;

import static org.testng.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Target_Website.libraries.Base;

public class SearchResultsPage extends Base {
	final static Logger logger = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {

	}

	public SearchResultsPage ensuringItemHasFishOilWord() {
		try {

			myLib.scrollUpDown(5000);

			myLib.assertionByText(By.xpath("//a[@data-test='product-title']"), "Fish Oil");

			WebElement pages = driver.findElement(By.xpath("//span[contains(text(),'page 1 of ')]"));
			String wholeText = pages.getText();

			// page 1 of 5

			String[] arrOfWholeText = wholeText.split("of", 2);
			String numberOfPagesString = arrOfWholeText[1].trim();

			int numberOfPages = Integer.parseInt(numberOfPagesString);

			for (int i = 0; i < numberOfPages - 1; i++) {

				myLib.customWait(1);

				myLib.clickButton(By.xpath("//a[@data-test='next']"));

				myLib.scrollUpDown(5000);

				myLib.customWait(1);

				myLib.assertionByText(By.xpath("//a[@data-test='product-title']"), "Fish Oil");

				myLib.customWait(1);

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}

		return this;
	}

	public SearchResultsPage verifyingLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total number of links: " + links.size());

		for (int i = 0; i < links.size(); i++) {
			WebElement elements = links.get(i);
			String url = elements.getAttribute("href");

			verifyLinks(url);
		}
		return this;
	}

	public SearchResultsPage verifyErrorText() {
		String errorMessage = driver.findElement(By.xpath("//div[@data-test='NLRTransparentMessage']")).getText();
		logger.info("Verifying the error message");
		String expectedErrorMessage = "We couldn’t find a match for your search.";
		assertEquals(errorMessage, expectedErrorMessage);
		return this;
	}

	public SearchResultsPage clickSortMenuButton(String sortMenutext) {
		myLib.clickButton(By.xpath("//button[@data-test='sortByContainer']"));
		myLib.customWait(1);
		myLib.clickButton(By.xpath("//div[text()='" + sortMenutext + "']"));
		myLib.customWait(1);
		return this;
	}

	public SearchResultsPage verifySortMenuTextPriceLowToHigh() {
		String sortMenuText = driver.findElement(By.xpath("//button[@data-test='sortByContainer']")).getText();
		logger.info("Verifying the sort menu text");
		String expectedSortMenuText = "Sort byPrice-low to high";
		assertEquals(sortMenuText, expectedSortMenuText);
		return this;
	}

	public SearchResultsPage verifySortMenuTextNewest() {
		String sortMenuText = driver.findElement(By.xpath("//button[@data-test='sortByContainer']")).getText();
		logger.info("Verifying the sort menu text");
		String expectedSortMenuText = "Sort byNewest";
		assertEquals(sortMenuText, expectedSortMenuText);
		return this;
	}

	public SearchResultsPage verifyItemResults() {
		String itemNumber = driver.findElement(By.xpath("//h2[@data-test='resultsHeading']")).getText();
		logger.info("The search result is :" + itemNumber);
		String[] arrOfItemNumber = itemNumber.split("results", 2);
		String numberOfResultsString = arrOfItemNumber[0].trim();
		int numberOfResults = Integer.parseInt(numberOfResultsString);
		myLib.customWait(2);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-test='next']")));
		// myLib.scrollUpDown(5000);
		ArrayList<WebElement> pagesItemsList = new ArrayList<>();
		List<WebElement> itemsList = driver.findElements(By.xpath("//a[@data-test='product-title']"));

		WebElement pages = driver.findElement(By.xpath("//span[contains(text(),'page 1 of ')]"));
		String wholeText = pages.getText();

		String[] arrOfWholeText = wholeText.split("of", 2);
		String numberOfPagesString = arrOfWholeText[1].trim();

		int numberOfPages = Integer.parseInt(numberOfPagesString);

		for (int i = 0; i < numberOfPages - 1; i++) {

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-test='next']")));
			WebElement next = driver.findElement(By.xpath("//a[@data-test='next']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);

			myLib.customWait(5);
			next = driver.findElement(By.xpath("//a[@data-test='next']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);

			myLib.customWait(3);
			next = driver.findElement(By.xpath("//a[@data-test='next']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);

			myLib.customWait(3);
			next = driver.findElement(By.xpath("//a[@data-test='next']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
			myLib.scrollUpDown(-100);
			myLib.customWait(5);
			itemsList = driver.findElements(By.xpath("//a[@data-test='product-title']"));
			for (WebElement element : itemsList) {
				pagesItemsList.add(element);
			}
			System.out.println("*****" + itemsList.size());
			System.out.println("*****" + pagesItemsList.size());
			myLib.customWait(1);
			myLib.clickButton(By.xpath("//a[@data-test='next']"));

		}

		Assert.assertEquals(pagesItemsList.size(), numberOfResults);
		return this;
	}

	private static void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.getRequestMethod();
			if (httpURLConnect.getResponseCode() >= 400) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + " is broken link");
			} else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + " is valid link");
			}

		} catch (Exception e) {
			logger.error("Error: ", e);
		}

	}

}
