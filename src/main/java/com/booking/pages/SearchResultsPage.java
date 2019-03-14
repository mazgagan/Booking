package com.booking.pages;

import com.booking.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends TestBase {
    @FindBy(id="ss")
    WebElement destTxtBox;

    @FindBy(xpath = "//div[@data-placeholder='Check-in Date']")
    WebElement checkIn;

    @FindBy(xpath = "//div[@data-placeholder='Check-out Date']")
    WebElement checkOut;

    @FindBy(id="group_adults")
    WebElement adultCntSelect;

    @FindBy(id="group_children")
    WebElement childCntSelect;

    @FindBy(id="no_rooms")
    WebElement noOfRoomsSelect;

    @FindBy(xpath = "//div[contains(@class,'sr_header ')]/h1")
    WebElement searchTxt;

    @FindBy(xpath = "//a[@class= 'hotel_name_link url']")
    List<WebElement> hotelLinks = new ArrayList<WebElement>();

    public SearchResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getSearchResultText() {
        return searchTxt.getText();
    }

    public boolean isDestTxtBoxPresent() {
        return destTxtBox.isDisplayed();
    }

    public WebElement getDestTxtBox() {
        return destTxtBox;
    }

    public boolean isCheckInPresent() {
        return checkIn.isDisplayed();
    }

    public WebElement getCheckIn() {
        return checkIn;
    }

    public boolean isCheckOutPresent() {
        return checkOut.isDisplayed();
    }

    public WebElement getCheckOut() {
        return checkOut;
    }

    public boolean isAdultCntSelectPresent() {
        return adultCntSelect.isDisplayed();
    }

    public WebElement getAdultCntSelect() {
        return adultCntSelect;
    }

    public boolean isChildCntSelectPresent() {
        return childCntSelect.isDisplayed();
    }

    public WebElement getChildCntSelect() {
        return childCntSelect;
    }

    public boolean isNoOfRoomsSelectPresent() {
        return noOfRoomsSelect.isDisplayed();
    }

    public WebElement getNoOfRoomsSelect() {
        return noOfRoomsSelect;
    }

    public ViewHotelPage clickHotelLink() {
        return new ViewHotelPage();
    }

    public List<WebElement> getHotelList() {
        return hotelLinks;
    }
}
