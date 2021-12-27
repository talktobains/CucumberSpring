package com.example.dbainsSpring.helpers;

import com.example.dbainsSpring.runners.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class VisibilityHelper {

    @Autowired
    private Hook hooks;

    /**
     * Waits until the given element is visible.
     * The element must be present on the DOM before the waiting starts
     * 
     * @param element Element to check
     */
    public void waitForVisibilityOf(WebElement element) {
        hooks.getWait().until(visibilityOf(element));
    }

    public void switchToFrame(String frameCSS){
        hooks.getDriver().switchTo().frame(hooks.getDriver().findElement(By.cssSelector(frameCSS)));
    }

    public void switchToDefaultFrame() {
        hooks.getDriver().switchTo().defaultContent();
    }

    /**
     * Waits for presence and visibility of the element matched by given selector.
     * The element can be present in the DOM or not before the waiting starts
     * 
     * @param by Selector of the element
     */
    public void waitForPresenceOf(By by) {
        hooks.getWait().until(visibilityOfElementLocated(by));
    }
}
