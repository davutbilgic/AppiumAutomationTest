package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Day02 {

    private AppiumDriver<MobileElement> driver;

    @Test
    public void sauceLabsTestWithAndroid() {
/*
saucelabs.com dan hesap acip
https://app.eu-central-1.saucelabs.com/user-settings   user settings sayfasindan Driver Creation linki alip asagiya url olarak ekledim.

https://saucelabs.com/platform/platform-configurator   sayfasindan da istedigim platform capabilities olusturabiliyorum. asagidaki capabilities i oradan olusturup usak testi basariyla calistirdim.

 */
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


    @Test
    public void sauceLabsTestWithIos() {
/*
soucelabs.com dan hesap acip
https://app.eu-central-1.saucelabs.com/user-settings   user settings sayfasindan Driver Creation linki alip asagiya url olarak ekledim.

https://saucelabs.com/platform/platform-configurator   sayfasindan da istedigim platform capabilities olusturabiliyorum. asagidaki capabilities i oradan olusturup usak testi basariyla calistirdim.

 */
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("browserName", "Safari");
            desiredCapabilities.setCapability("appium:deviceName", "iPhone 13 Pro Max Simulator");
            desiredCapabilities.setCapability("appium:platformVersion", "15.0");
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion", "1.22.0");
            desiredCapabilities.setCapability("sauce:options", sauceOptions);


            driver = new AppiumDriver<>(new URL("https://oauth-db.davutbilgic-02da2:f0d0e7f8-b681-46f3-874d-f5896b63da90@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), desiredCapabilities);

            driver.get("https://www.cybertekschool.com");

            Thread.sleep(5000);

            //close the app at the end
            driver.closeApp();
            driver.quit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void browserStacktTest() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            // Set your access credentials
            caps.setCapability("browserstack.user", "davutbilgic_LiZMJF");
            caps.setCapability("browserstack.key", "RenS4Vq1Y4by62pUAJg1");

            // Specify device and os_version for testing
            caps.setCapability("device", "Google Pixel 3");
            caps.setCapability("os_version", "9.0");

            // Set other BrowserStack capabilities
            caps.setCapability("project", "First Java Project");
            caps.setCapability("build", "browserstack-build-1");
            caps.setCapability("name", "first_test");
            caps.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);


            driver = new AppiumDriver<>(new URL("http://hub.browserstack.com/wd/hub"), caps);


            driver.get("https://qa2.vytrack.com");
            driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
            driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

            Thread.sleep(3000);

            // Invoke driver.quit() after the test is done to indicate that the test is completed.
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


