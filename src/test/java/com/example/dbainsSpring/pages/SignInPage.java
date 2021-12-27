package com.example.dbainsSpring.pages;

import com.example.dbainsSpring.helpers.VisibilityHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInPage implements BasePage {

    @Autowired
    private VisibilityHelper visibilityHelper;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailAddress;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;

    @FindBy(how = How.NAME, using = "SubmitLogin")
    private WebElement signInToApplication;

    public void enterEmailAddress(String email){
        visibilityHelper.waitForVisibilityOf(emailAddress);
        emailAddress.sendKeys(email);
    }

    public void enterPassword(String pass){
        password.sendKeys(pass);
    }

    public void clickSignIn() {
        signInToApplication.click();
    }

    public void verifySignInSuccessful(){
        visibilityHelper.waitForPresenceOf(By.cssSelector("div.header_user_info span"));
    }
}