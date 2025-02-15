package com.velespit.step_definitions;

import com.velespit.utilities.BrowserUtils;
import com.velespit.utilities.Driver;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hooks {


    @Before
    public void setupMethod(Scenario scenario) {
        System.out.println("------> @Before: Running before each scenario");
        String testCaseNumber = extractTestCaseNumber(scenario.getName());
        if (testCaseNumber != null) {
            TestContext.setTestCaseNumber(testCaseNumber);
            System.out.println(testCaseNumber);
        }
    }


    /**
     * Scenario interface comes with Cucumber, no need extra steps, you can definitely use one less if, but since they
     * are doing completely different things, i wanted to separate them.
     * @param scenario
     */
    @After
    public void teardownMethod(Scenario scenario) {

        if (scenario.isFailed()) {
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

    /**
     * Using it for extracting Test Case Number from Scenario, other approaches are welcomed but i used this.
     * @param scenarioName
     * @return
     */
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
