package com.velespit.step_definitions;

import com.velespit.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApachePOI {

    //Method chaining purposes nothing special
    BrowserUtils browserUtils = new BrowserUtils();

    @When("i read username and password {string}")
    public void i_read_username_and_password(String testCaseNumber) {
        browserUtils.findUsername(testCaseNumber)
                .findPassword(testCaseNumber);
        //Assert.fail("Debug!");
    }


    @Then("test needs to pass {string}")
    public void test_needs_to_pass(String testCaseNumber) {

    }
}


