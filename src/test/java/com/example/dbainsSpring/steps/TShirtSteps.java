package com.example.dbainsSpring.steps;

import com.example.dbainsSpring.pages.TShirtsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class TShirtSteps {

    @Autowired
    private TShirtsPage tShirtsPage;

    @When("^I place and confirm an order for a new TShirt$")
    public void placeOrderForNewTShirt() {
        tShirtsPage.clickFadedTShirt();
        tShirtsPage.clickAddToCart();
        tShirtsPage.clickProceedToCheckout();
        tShirtsPage.clickProceedToCheckoutOnSummaryTab();
        tShirtsPage.clickProceedToCheckoutOnAddressTab();
        tShirtsPage.clickAgreeToTermsCheckbox();
        tShirtsPage.clickProceedToCheckoutOnShippingTab();
        tShirtsPage.clickPayByCheque();
        tShirtsPage.clickConfirmOrder();
        tShirtsPage.saveOrderReference();
    }

    @Then("^The corresponding Order gets displayed in Order History$")
    public void verifyOrder() {
        tShirtsPage.navigateToOrderHistory();
        tShirtsPage.verifyOrderInOrderHistory();
    }
}
