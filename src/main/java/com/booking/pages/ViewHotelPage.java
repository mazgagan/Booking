package com.booking.pages;

import com.booking.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ViewHotelPage extends TestBase {

    public ViewHotelPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@id= 'hp_hotel_name']")
    WebElement hotelName;

    @FindBy(xpath = "//*[@class= 'hprt-price-price-standard \n']")
    List<WebElement> hotelLinks = new ArrayList<WebElement>();

    @FindBy(xpath = "//button[starts-with(@id,'b_tt_holder_') and contains((text(),'I\'ll reserve'))]")
    WebElement reserveBtn;

    public WebElement getHotelName() {
        return hotelName;
    }

    public List<WebElement> getPriceList() {
        return hotelLinks;
    }

    public boolean isReserveBtnPresent() {
        return reserveBtn.isDisplayed();
    }

    public CheckOutPage clickReserveBtn(){
        reserveBtn.click();
        return new CheckOutPage();
    }
}
