package com.booking.tests;

import com.booking.base.TestBase;
import com.booking.pages.CheckOutPage;
import com.booking.pages.HomePage;
import com.booking.pages.SearchResultsPage;
import com.booking.pages.ViewHotelPage;
import com.booking.utils.CommonUtil;
import com.booking.utils.TestUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends TestBase {
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ViewHotelPage viewHotelPage;
    CheckOutPage checkOutPage;
    String sheetName = "Booking";
    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialize();
        homePage = new HomePage();
    }

    @DataProvider
    public Object[][] getHomePageTestData() {
        return TestUtil.getTestData(sheetName);
    }

    // Validate title is present
    @Test(dataProvider = "getHomePageTestData")
    public void bookingTest (String city) {
        String actTitle = homePage.getTitle();
//        Assert.assertTrue(
//                actTitle.matches(
//                        "Booking\\.com:\\s+(\\d{0,3},)?(\\d{3},)?\\d{0,3}\\shotel\\sand\\s" +
//                                "property\\slistings\\sworldwide\\.\\s\\d{1,3}\\+" +
//                                "million\\shotel\\sreviews\\."));
        Assert.assertTrue(homePage.isDestBoxEnabled());
        Assert.assertTrue(homePage.isDestBoxEnabled());
        Assert.assertTrue(homePage.isGuestCountEnabled());
        Assert.assertTrue(homePage.isSearchBtnEnabled());

        homePage.getDestination().sendKeys(city);
        homePage.getStayDates().click();
        String calMonthYear = homePage.getStartMonth().getText();
        String monthYear =TestUtil.getMonth() + " " + TestUtil.getYear();
        while (!calMonthYear.equals(monthYear)) {
            homePage.getNextMonth().click();
            calMonthYear = homePage.getStartMonth().getText();
        }
        homePage.getStartDate().click();
        homePage.getEndDate().click();
        searchResultsPage = homePage.search();
        actTitle = searchResultsPage.getTitle();
        Assert.assertTrue(
                actTitle.matches(
                        "Booking\\.com:\\sHotels\\sin\\s"
                                + city
                                + "\\.\\sBook\\syour\\shotel\\snow!"
                ));
        Assert.assertTrue(searchResultsPage.isDestTxtBoxPresent());
        Assert.assertTrue(searchResultsPage.isCheckInPresent());
        Assert.assertTrue(searchResultsPage.isCheckOutPresent());
        Assert.assertTrue(searchResultsPage.isAdultCntSelectPresent());
        Assert.assertTrue(searchResultsPage.isChildCntSelectPresent());
        Assert.assertTrue(searchResultsPage.isNoOfRoomsSelectPresent());
        Assert.assertEquals("expected does not match actual",
                city, searchResultsPage.getDestTxtBox().getAttribute("value"));
        Assert.assertTrue(searchResultsPage.getSearchResultText().contains(city));
        List<WebElement> hotelLinks = searchResultsPage.getHotelList();
        int randomNum = CommonUtil.genRandom();
        int noOfHotels = hotelLinks.size();
        String expHotelname = "";
        if (noOfHotels == 0) {
            Assert.assertTrue("No hotels found", false);
        } else if (noOfHotels < randomNum){
            expHotelname = hotelLinks.get(0).getText();
            hotelLinks.get(0).click();
        } else {
            expHotelname = hotelLinks.get(randomNum).getText();
            hotelLinks.get(randomNum).click();
        }
        expHotelname = expHotelname.substring(0,expHotelname.indexOf("\n"));
        viewHotelPage = CommonUtil.switchTab();
        actTitle = viewHotelPage.getTitle();
        Assert.assertTrue("title donot match", actTitle.contains(city));
        String actHotelName = viewHotelPage.getHotelName().getText().substring(6);
        Assert.assertEquals("hotel name does not match", expHotelname, actHotelName);
        List<WebElement> priceList = viewHotelPage.getPriceList();
        Double minPrice = Double.MAX_VALUE;
        WebElement priceElement = null;
        for(int i = 0; i < priceList.size(); i++) {
            String tempPrice = priceList.get(i).getText();
            tempPrice = tempPrice.substring(1);
            Double price = Double.parseDouble(tempPrice);
            if (price < minPrice) {
                minPrice = price;
                priceElement = priceList.get(i);
            }
        }

        WebElement el = priceElement.findElement(By.xpath("ancestor::td[1]"));
        el = el.findElement(By.xpath("following-sibling::td[2]"));
        el = el.findElement(By.xpath(".//select[@class='hprt-nos-select']"));
        Select roomDDL = new Select(el);
        roomDDL.selectByIndex(1);
        Assert.assertTrue("Reserve button not visible", viewHotelPage.isReserveBtnPresent());
        checkOutPage = viewHotelPage.clickReserveBtn();
        actTitle = checkOutPage.getTitle();
        Assert.assertEquals("Title does not match for checkout page",
                "Booking.com: Your Details", actTitle);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
