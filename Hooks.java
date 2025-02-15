package com.velespit.step_definitions;

import com.velespit.utilities.BrowserUtils;
import com.velespit.utilities.Driver;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
In this class we will be able to create "pre" and "post"
condition for All the scenarios and even steps.
 */

public class Hooks {


    //import the @Before from io.cucumber.java
    @Before //(order = 1)
    public void setupMethod(Scenario scenario) {
        System.out.println("------> @Before: Running before each scenario");
        String testCaseNumber = extractTestCaseNumber(scenario.getName());
        if (testCaseNumber != null) {
            TestContext.setTestCaseNumber(testCaseNumber);
            System.out.println(testCaseNumber);
        }
    }


    /*
    @After will be executed automatically after EVERY scenario in the project.
     */
    @After
    public void teardownMethod(Scenario scenario) {

        if (scenario.isFailed()) {
            //we are using byte because pixels are storing with bytes because it consumes less memory. We need to minimize data storage.
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }


        BrowserUtils browserUtils = new BrowserUtils();

        String testCaseNumber = TestContext.getTestCaseNumber();

        System.out.println(testCaseNumber);

        if (testCaseNumber != null) {
            if (scenario.isFailed()) {
                browserUtils.fail(testCaseNumber);
            } else {
                browserUtils.pass(testCaseNumber);
            }
        }

        TestContext.clear();

        System.out.println("---> @After: RUNNING AFTER EACH SCENARIO");


    }

    private String extractTestCaseNumber(String scenarioName) {
        // Use regex to extract number from scenario
        Pattern pattern = Pattern.compile("^(US\\d{2}-AC\\d{2}-TC\\d{2})");
        Matcher matcher = pattern.matcher(scenarioName);

        if (matcher.find()) {
            return matcher.group(1); // Return matched test case number
        }
        return null;
    }


}
