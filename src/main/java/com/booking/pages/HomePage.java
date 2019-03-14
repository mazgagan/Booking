package com.booking.pages;

import com.booking.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    @FindBy(id = "ss")
    WebElement destination;

    @FindBy(xpath = "//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']")
    WebElement stayDates;

    @FindBy(xpath = "//span[@class='xp__guests__count']")
    WebElement guestCnt;

    @FindBy(xpath = "//button[@type='submit' and span='Search']")
    WebElement searchBtn;

    @FindBy(xpath = "//div[@class = 'bui-calendar__control bui-calendar__control--next']")
    WebElement nextMonth;

    @FindBy(xpath = "//div[@class = 'bui-calendar__content']/div[1]/div[1]")
    WebElement startMonth;

    @FindBy(xpath = "//div[@class = 'bui-calendar__content']/div[1]/table/tbody/tr[2]/td[contains(text(),'1')]")
    WebElement startDate;

    @FindBy(xpath = "//div[@class = 'bui-calendar__content']/div[1]/table/tbody/tr[2]/td[contains(text(),'2')]")
    WebElement endDate;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage search() {
        searchBtn.click();
        return new SearchResultsPage();
    }

    public boolean isDestBoxEnabled() {
        return destination.isEnabled();
    }

    public boolean isDatesBoxEnabled() {
        return stayDates.isEnabled();
    }

    public boolean isGuestCountEnabled() {
        return guestCnt.isEnabled();
    }

    public boolean isSearchBtnEnabled() {
        return searchBtn.isEnabled();
    }

    public WebElement getDestination() {
        return destination;
    }

    public WebElement getStayDates() {
        return stayDates;
    }

    public WebElement getNextMonth() {
        return nextMonth;
    }

    public WebElement getStartMonth() {
        return startMonth;
    }

    public WebElement getStartDate (){
        return startDate;
    }

    public WebElement getEndDate (){
        return endDate;
    }
}
