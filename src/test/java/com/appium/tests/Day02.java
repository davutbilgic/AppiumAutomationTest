package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Day02 {

    private AppiumDriver<MobileElement> driver;

    @Test
    public void testRemoteAndroidOnSouceLabs() {

        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("appium:deviceName", "Google Pixel 3 GoogleAPI Emulator");
            desiredCapabilities.setCapability("appium:platformVersion", "10.0");
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion", "1.20.2");
            desiredCapabilities.setCapability("sauce:options", sauceOptions);

//            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/etsy.apk");

            driver = new AppiumDriver<>(new URL("https://oauth-db.davutbilgic-02da2:f0d0e7f8-b681-46f3-874d-f5896b63da90@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), desiredCapabilities);


            Thread.sleep(5000);

            MobileElement you = driver.findElement(MobileBy.AccessibilityId("You tab, 4 of 5"));
            you.click();
            Thread.sleep(2000);

            MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));
            settings.click();
            Thread.sleep(2000);

            MobileElement checkbox = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));
            checkbox.click();
            Thread.sleep(3000);

            //verify after click the checkbox it is not selected
            Assert.assertFalse(driver.findElement(By.id("com.etsy.android:id/settings_checkbox")).isSelected());
            Thread.sleep(5000);

            //close the app at the end
            driver.closeApp();
            driver.quit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }










}


