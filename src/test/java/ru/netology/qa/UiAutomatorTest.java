package ru.netology.qa;


import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;


public class UiAutomatorTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    @Test
    public void emptyString() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.userInput.click();
        mainScreen.userInput.sendKeys(" ");
        mainScreen.buttonChange.click();
        Assertions.assertEquals("Hello UiAutomator!", mainScreen.textToBeChanged.getText());
    }

    @Test
    public void changingTheLabel() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.userInput.click();
        mainScreen.userInput.sendKeys("I'm really tired!");
        mainScreen.buttonChange.click();
        Assertions.assertEquals("I'm really tired!", mainScreen.textToBeChanged.getText());
    }

    @Test
    public void newTextInANewTab() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.userInput.click();
        mainScreen.userInput.sendKeys("It took half a day for the tests to work, it's hard!");
        mainScreen.buttonActivity.click();
        Assertions.assertEquals("It took half a day for the tests to work, it's hard!", mainScreen.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
