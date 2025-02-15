package com.velespit.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class BrowserUtils {
    /*
    This method will accept int (in seconds)
    and execute Thread.sleep method for given duration
    Arg: int second
     */
    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }

    public static void switchWindowAndVerify(String expectedInURL, String expectedInTitle) {

        //Return and store all window handles in a Set.
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowHandles) {

            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInURL)) {
                break;
            }
        }

        //5. Assert:Title contains “Etsy”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    public static void verifyTitle(String expectedTitle) {
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitleContains(String expectedInTitle) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    public BrowserUtils findUsername(String testCase) {
        String filePath = ConfigurationReader.getProperty("filePath");
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean found = false;

            for (Row row : sheet) {
                Cell testCaseCell = row.getCell(3); // Test case number column
                if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCase)) {
                    Cell usernameCell = row.getCell(7); // Password number column
                    if (usernameCell != null) {
                        String password = usernameCell.getStringCellValue().trim().split(",")[1].trim().split(":")[1].trim();
                        System.out.println("Username: " + password);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Test case not found: " + testCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public BrowserUtils findPassword(String testCase) {
        String filePath = ConfigurationReader.getProperty("filePath");

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean found = false;

            for (Row row : sheet) {
                Cell testCaseCell = row.getCell(3); // Test case number column
                if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCase)) {
                    Cell passwordCell = row.getCell(7); // Password number column
                    if (passwordCell != null) {
                        String password = passwordCell.getStringCellValue().trim().split(",")[2].trim().split(":")[1].trim();
                        System.out.println("Password: " + password);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Test case not found: " + testCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;

    }

    public BrowserUtils pass(String testCase){

        String filePath = ConfigurationReader.getProperty("filePath");

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean found = false;

            for (Row row : sheet) {
                Cell testCaseCell = row.getCell(3);
                if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCase)) {
                    Cell statusCell = row.getCell(12);
                    if (statusCell != null) {
                        statusCell.setCellValue("PASSED");
                        found = true;
                        break;
                    }
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            if (!found) {
                System.out.println("Test case not found: " + testCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;

    }

    public BrowserUtils fail(String testCase){

        String filePath = ConfigurationReader.getProperty("filePath");

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean found = false;

            for (Row row : sheet) {
                Cell testCaseCell = row.getCell(3); // Test case numarasının olduğu sütun
                if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCase)) {
                    Cell statusCell = row.getCell(12); // Status olduğu sütun
                    if (statusCell != null) {
                        statusCell.setCellValue("FAILED");
                        found = true;
                        break;
                    }
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            if (!found) {
                System.out.println("Test case not found: " + testCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;

    }


    /*
    This method accepts WebElement target,
    and waits for that WebElement not to be displayed on the page
     */
    public static void waitForInvisibilityOf(WebElement target) {
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        //use the 'wait' object with the proper syntax to create explicit wait conditions.
        wait.until(ExpectedConditions.invisibilityOf(target));
    }

//    public static void waitForText(WebElement page, String searchKeyword){
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.attributeContains(page,"data-replicated-value", searchKeyword));
//    }

    public static void waitForText(WebElement element, String attribute) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> {
            String value = element.getAttribute(attribute);
            System.out.println("Mevcut Değer: " + value); // Debug için
            return value != null && !value.isEmpty();
        });
    }

    public static void verifyUrl() {
        Driver.getDriver().getCurrentUrl();
    }


    /*
    This method accepts String title,
    and waits for that Title to contain given String value.
     */
    public static void waitForTitleContains(String title) {
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        //use the 'wait' object with the proper syntax to create explicit wait conditions.
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitForTitle(String title, int time) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));

        wait.until(ExpectedConditions.titleIs(title));
    }

}
