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
public class MyAccountPage implements BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

    @Autowired
    private VisibilityHelper visibilityHelper;

    @FindBy(how = How.CSS, using = "a.account")
    private WebElement myAccount;

    @FindBy(how = How.CSS, using = "ul[class='myaccount-link-list'] a[title='Information']")
    private WebElement myPersonalInformation;

    @FindBy(how = How.CSS, using = "input#firstname")
    private WebElement firstNameTextBox;

    @FindBy(how = How.CSS, using = "input#old_passwd")
    private WebElement currentPassword;

    @FindBy(how = How.CSS, using = "button[name='submitIdentity']")
    private WebElement saveButton;

    @FindBy(how = How.CSS, using = "div.header_user_info span")
    private WebElement accountName;

    public void clickAccountNameOnTopBar() {
        visibilityHelper.waitForVisibilityOf(myAccount);
        myAccount.click();
    }

    public void clickMyPersonalInformation() {
        visibilityHelper.waitForVisibilityOf(myPersonalInformation);
        myPersonalInformation.click();
    }

    public void enterNameInFirstNameBox(String name) {
        visibilityHelper.waitForVisibilityOf(firstNameTextBox);
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(name);
    }

    public void enterCurrentPassword() {
        visibilityHelper.waitForVisibilityOf(currentPassword);
        currentPassword.sendKeys("Selenium101");
    }

    public void clickSaveButton() {
        visibilityHelper.waitForVisibilityOf(saveButton);
        saveButton.click();
    }

    public void verifyAccountName(String expectedName) {
        visibilityHelper.waitForVisibilityOf(accountName);
        String actualName = accountName.getText();
        if (actualName.equalsIgnoreCase(expectedName)) {
            LOGGER.info("First name has been updated as " + expectedName + ".");
        } else {
            LOGGER.warn("First name has not been updated as " + expectedName + ".");
        }
    }

}