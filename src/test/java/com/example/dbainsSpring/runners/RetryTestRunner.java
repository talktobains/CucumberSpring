package com.example.dbainsSpring.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "@results/failedruns.txt",
        plugin = {"pretty", "html:results/cucumber-reports.html", "json:results/cucumber.json", "junit:results/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@smoke")
public class RetryTestRunner {

}
