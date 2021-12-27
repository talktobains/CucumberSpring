package com.example.dbainsSpring.runners;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.TRUE;
import static org.springframework.util.ObjectUtils.isEmpty;


@Component
public class Hook {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Hook.class);

    @Value("${selenium.browser}")
    private String browser;

    @Value("${selenium.browser.headless}")
    private Boolean headless;

    @Value("${selenium.browser.remote}")
    private String remote;

    @Value("${target.application.baseUrl}")
    private String baseUrl;

    private WebDriver driver;

    private WebDriverWait wait;

    @PostConstruct
    public void initialize() {

        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (isDriverLoaded()) {
                LOGGER.info("Shutdown signal detected: Closing opened drivers");
                closeDriver();
                LOGGER.info("Opened drivers closed");
            }
        }));
        // --
    }

    private boolean isDriverLoaded() {
        return driver != null;
    }

    public WebDriver getDriver() {
        if (isEmpty(driver)) {
            initialiseDriver();
        }
        return driver;
    }

    public WebDriverWait getWait() {
        if (isEmpty(wait)) {
            initialiseDriver();
        }
        return wait;
    }

    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }

    public void closeDriver() {
        if (driver == null) {
            return;
        }

        driver.quit();
        driver = null;
    }

    private void initialiseDriver() {

        // Prevent mem leak
        if (!isEmpty(driver)) {
            closeDriver();
        }

        // Disable driver log output
        System.setProperty("webdriver.chrome.silentOutput", "true");

        if ("chrome".equals(browser)) {
            setChromeDriver();
        } else if ("firefox".equals(browser)) {
            // TODO : firefox driver
        }

        // Navigate
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        goToBaseUrl();
    }

    private void setChromeDriver() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "chromedriver.exe");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        // Headless mode
        if (TRUE.equals(headless)) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("window-size=1920,1080");
        }

        // Remote mode for Selenium Grid
        if ("grid".equals(remote)) {
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            } catch (MalformedURLException e) {
                LOGGER.error("Error", e);
            }
        } else {
            driver = new ChromeDriver(chromeOptions);
        }
        wait = new WebDriverWait(driver, 60, 5000);
    }

    private void goToBaseUrl() {
        driver.navigate().to(baseUrl);
    }

}