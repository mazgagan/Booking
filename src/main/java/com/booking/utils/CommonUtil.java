package com.booking.utils;

import com.booking.base.TestBase;
import com.booking.pages.ViewHotelPage;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil extends TestBase {
    public static int genRandom() {
        return (int) (Math.random()*2);
    }

    public static ViewHotelPage switchTab() {
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        return new ViewHotelPage();
    }
}
