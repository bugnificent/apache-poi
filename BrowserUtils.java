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


    /**
     * Method for find username for the specified cell using testCase as an argument, its possible to use indexes
     * to move right, but since cells doesnt change at all safe to use this approach.
     * @param testCase
     * @return
     */
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
                        //I included other test-datas in my Cell like URL thats why i used split and trim, may not be necessary.
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

    /**
     * Same approach as findUsername.
     * @param testCase
     * @return
     */

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
                        //I included other test-datas in my Cell like URL thats why i used split and trim, may not be necessary.
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

    /**
     * Method for Test Case status change, can be simplified of course.
     * @param testCase
     * @return
     */
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

    /**
     * Method for Test Case status change, can be simplified of course.
     * @param testCase
     * @return
     */
    public BrowserUtils fail(String testCase){

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
 
}


