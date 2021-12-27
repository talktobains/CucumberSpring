package com.example.dbainsSpring.pages;

import com.example.dbainsSpring.helpers.VisibilityHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TShirtsPage implements BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(TShirtsPage.class);

    @Autowired
    private VisibilityHelper visibilityHelper;

    @FindBy(how = How.CSS, using = "div.product-image-container a img")
    private WebElement fadedTShirt;

    @FindBy(how = How.CSS, using = "div.fancybox-inner")
    private WebElement addToCartPopUp;

    @FindBy(how = How.CSS, using = "p#add_to_cart button.exclusive")
    private WebElement addToCart;

    @FindBy(how = How.CSS, using = "div.button-container a[title='Proceed to checkout']")
    private WebElement proceedToCheckout;

    @FindBy(how = How.CSS, using = "p a[title='Proceed to checkout']")
    private WebElement proceedToCheckoutSummaryTab;

    @FindBy(how = How.CSS, using = "button[name='processAddress']")
    private WebElement proceedToCheckoutAddressTab;

    @FindBy(how = How.CSS, using = "p[class='checkbox'] div span")
    private WebElement agreeToTermsCheckbox;

    @FindBy(how = How.CSS, using = "p[class='cart_navigation clearfix'] button[name='processCarrier']")
    private WebElement proceedToCheckoutShippingTab;

    @FindBy(how = How.CSS, using = "p.payment_module a.cheque")
    private WebElement payByChequeButton;

    @FindBy(how = How.CSS, using = "p#cart_navigation button")
    private WebElement confirmOrderButton;

    @FindBy(how = How.XPATH, using = "//div[@class='box order-confirmation']")
    private WebElement orderReference;

    @FindBy(how = How.CSS, using = "p a[title='Back to orders']")
    private WebElement backToOrderHistory;

    @FindBy(how = How.CSS, using = "tr.first_item td[class='history_link bold footable-first-column'] a")
    private WebElement orderReferenceOnOrderHistory;

    private static String orderNumber;

    public void clickFadedTShirt() {
        visibilityHelper.waitForVisibilityOf(fadedTShirt);
        fadedTShirt.click();
    }

    public void clickAddToCart() {
        visibilityHelper.waitForVisibilityOf(addToCartPopUp);
        String frameCSS = "iframe[class='fancybox-iframe']";
        visibilityHelper.switchToFrame(frameCSS);
        visibilityHelper.waitForVisibilityOf(addToCart);
        addToCart.click();
        visibilityHelper.switchToDefaultFrame();
    }

    public void clickProceedToCheckout() {
        visibilityHelper.waitForVisibilityOf(proceedToCheckout);
        proceedToCheckout.click();
    }

    public void clickProceedToCheckoutOnSummaryTab() {
        visibilityHelper.waitForVisibilityOf(proceedToCheckoutSummaryTab);
        proceedToCheckoutSummaryTab.click();
    }

    public void clickProceedToCheckoutOnAddressTab() {
        visibilityHelper.waitForVisibilityOf(proceedToCheckoutAddressTab);
        proceedToCheckoutAddressTab.click();
    }

    public void clickAgreeToTermsCheckbox() {
        visibilityHelper.waitForVisibilityOf(agreeToTermsCheckbox);
        agreeToTermsCheckbox.click();
    }

    public void clickProceedToCheckoutOnShippingTab() {
        visibilityHelper.waitForVisibilityOf(proceedToCheckoutShippingTab);
        proceedToCheckoutShippingTab.click();
    }

    public void clickPayByCheque() {
        visibilityHelper.waitForVisibilityOf(payByChequeButton);
        payByChequeButton.click();
    }

    public void clickConfirmOrder() {
        visibilityHelper.waitForVisibilityOf(confirmOrderButton);
        confirmOrderButton.click();
    }

    public void saveOrderReference(){
        visibilityHelper.waitForVisibilityOf(orderReference);
        orderNumber = orderReference.getText().split("reference ")[1].split(".\n- An email")[0];
    }

    public void navigateToOrderHistory(){
        visibilityHelper.waitForVisibilityOf(backToOrderHistory);
        backToOrderHistory.click();
    }

    public void verifyOrderInOrderHistory() {
        visibilityHelper.waitForVisibilityOf(orderReferenceOnOrderHistory);
        String firstOrderInHistory = orderReferenceOnOrderHistory.getText();
        if(firstOrderInHistory.equalsIgnoreCase(orderNumber)) {
            LOGGER.info("Order " + orderNumber + " is present in OrderHistory.");
        } else {
            LOGGER.warn("Order " + orderNumber + " is not present in OrderHistory.");;
        }
    }

}