package com.booking.utils;

import com.booking.base.TestBase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class TestUtil extends TestBase {
    public static String DATASHEET_PATH = "C:\\Users\\mazga\\Documents\\JavaPrograms\\BookingTest\\src\\main\\java\\com\\booking\\testdata\\TestData.xlsx";

    static Workbook book;
    static Sheet sheet;

    public static Object[][] getTestData (String sheetName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(DATASHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(fis);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] testData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                testData[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return testData;
    }

    public static String getMonth() {
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH) + 1];
        return month;
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String yearInString = String.valueOf(year);
        return yearInString;
    }
}
