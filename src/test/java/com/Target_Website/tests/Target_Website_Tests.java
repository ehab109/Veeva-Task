package com.Target_Website.tests;

import org.testng.annotations.Test;

import com.Target_Website.libraries.Base;
import com.Target_Website.pages.HomePage;
import com.Target_Website.pages.SearchResultsPage;

public class Target_Website_Tests extends Base {

	@Test(enabled = false, priority = 1)
	public void testCase_SFTC_100() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();
	}

	@Test(enabled = false, priority = 2)
	public void testCase_SFTC_101() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();

		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.ensuringItemHasFishOilWord();
	}

	@Test(enabled = true, priority = 3)

	public void testCase_SFTC_102() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();

	    SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.verifyItemResults();
	}

	@Test(enabled = false, priority = 4)

	public void testCase_SFTC_103() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.verifyingLinks();
	}
	
	@Test(enabled = false, priority = 5)

	public void testCase_SFTC_104() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fis")
		.verifySuggestedDropDownText();
	}
	
	@Test(enabled = false, priority = 6)

	public void testCase_SFTC_105() {
		HomePage myHP = new HomePage();
		myHP.searchItem("S@$%").clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.verifyErrorText();
	}
	
	@Test(enabled = false, priority = 7)

	public void testCase_SFTC_106() {
		HomePage myHP = new HomePage();
		myHP.searchItem("5986247").clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.verifyErrorText();
	}
	
	@Test(enabled = false, priority = 8)

	public void testCase_SFTC_107() {
		HomePage myHP = new HomePage();
		myHP.searchItem(" ").clickingOnSearchButton();
	}
	
	@Test(enabled = false, priority = 9)

	public void testCase_SFTC_200() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.clickSortMenuButton("Price-low to high").verifySortMenuTextPriceLowToHigh();
		
	}
	
	@Test(enabled = false, priority = 10)

	public void testCase_SFTC_300() {
		HomePage myHP = new HomePage();
		myHP.searchItem("Fish Oil").clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.clickSortMenuButton("Newest").verifySortMenuTextNewest();
		
	}
	

}
