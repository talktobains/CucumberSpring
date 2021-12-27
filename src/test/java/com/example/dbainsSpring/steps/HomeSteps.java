package com.example.dbainsSpring.steps;

import com.example.dbainsSpring.pages.HomePage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeSteps {

    @Autowired
    private HomePage homePage;

    @Given("^I click on SignIn button on Homepage$")
    public void iClickOnSignInButton() {
        homePage.clickSignIn();
    }

    @Given("^I press the T-Shirts button in the home page navigation bar$")
    public void iPressTheTShirtsButtonInTheHomePage() {
        homePage.clickTShirtsButton();
    }
}
