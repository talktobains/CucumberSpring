package com.example.dbainsSpring.pages;

import com.example.dbainsSpring.helpers.VisibilityHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage implements BasePage {

    @Autowired
    private VisibilityHelper visibilityHelper;

    @FindBy(xpath="//a[@title='Dresses']/parent::li/following-sibling::li/a[@title='T-shirts']")
    private WebElement tShirtsButton;

    @FindBy(css="a[title='Log in to your customer account']")
    private WebElement signInButton;

    public void clickSignIn() {
        visibilityHelper.waitForVisibilityOf(signInButton);
        signInButton.click();
    }

    public void clickTShirtsButton() {
        visibilityHelper.waitForVisibilityOf(tShirtsButton);
        tShirtsButton.click();
    }

}