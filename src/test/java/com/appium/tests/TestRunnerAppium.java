package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class TestRunnerAppium {

    private AppiumDriver<MobileElement> driver;

    @Test
    public void test1() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.1");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");

            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");

            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);


            Thread.sleep(3000);

            WebElement getStarted = driver.findElement(By.xpath("//*[@text='Get Started']"));
            getStarted.click();

            Thread.sleep(3000);

            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //we use android phone
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            //version of android
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.1");
            //name of the device, if it is real device we need to pass UUID parameter
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");


            //either you specify app --> //path/to/app.apk
            //or if app is already installed, you need to specify appActivity and appPackage
            //this info you can find in the internet(ex googling: android calculator app activity and app package//line 33,34), at work ask developers

            desiredCapabilities.setCapability("appPackage", "com.android.calculator2");//application's package name
            desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");//app's MainActivity i.e. LAUNCHER activity name

            //
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            //http://localhost:4723/wd/hub         address of the appium server. if you have appium server on the same computer jus use localhost
            //4723 default port number. we need to provide 2 parameters: URL of appium servers and desired capabilities.
            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

            Thread.sleep(3000);


            //verify 2+2 is returning 4  -----------
            MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
            //mobileBy child class of by
            MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
            MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));
            MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

            digit2.click();
            plus.click();
            digit2.click();
            equals.click();

            //get the text of mobile element of result
            String actualResultText = result.getText();
            Assert.assertEquals("4", actualResultText);

            //verify 4*5=20
            MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
            MobileElement digit5 = driver.findElement(By.id("com.android.calculator2:id/digit_5"));
            MobileElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));

            digit4.click();
            multiply.click();
            digit5.click();
            equals.click();

            actualResultText = result.getText();
            Assert.assertEquals("20", actualResultText);


            //close the app at the end
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
