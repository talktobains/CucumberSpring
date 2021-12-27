package com.example.dbainsSpring.steps;

import com.example.dbainsSpring.pages.SignInPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class SignInSteps {

    @Autowired
    private SignInPage signInPage;

    @When("^I login to application using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSearchInTheSearchInputOfTheHomePage(String email, String password) {
        signInPage.enterEmailAddress(email);
        signInPage.enterPassword(password);
        signInPage.clickSignIn();
    }

    @Then("^I am able to Login with the given User$")
    public void verifyLogin() {
        signInPage.verifySignInSuccessful();
    }

}
