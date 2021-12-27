package com.example.dbainsSpring.steps;

import com.example.dbainsSpring.pages.MyAccountPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class MyAccountSteps {

    @Autowired
    private MyAccountPage myAccountPage;

    @Given("^I navigate to MyAccount$")
    public void iGoToMyAccount() {
        myAccountPage.clickAccountNameOnTopBar();
    }

    @When("^I update FirstName as \"([^\"]*)\"$")
    public void iUpdatePersonalDetails(String firstName) {
        myAccountPage.clickMyPersonalInformation();
        myAccountPage.enterNameInFirstNameBox(firstName);
        myAccountPage.enterCurrentPassword();
        myAccountPage.clickSaveButton();
    }

    @Then("^I verify FirstName has been updated to \"([^\"]*)\"$")
    public void iVerifyPersonalDetails(String firstName) {
        myAccountPage.verifyAccountName(firstName);
    }

}
