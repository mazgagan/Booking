package com.booking.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static WebDriver driver;
    private static Properties prop;

    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") +
                            "/src/main/java/com/booking/config/config.properties"
            );
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        String browserName = prop.getProperty("browser");
        if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "C:\\Users\\mazga\\Downloads\\geckodriver-v0.24.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\mazga\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    public static String getTitle() {
        return driver.getTitle();
    }
}
