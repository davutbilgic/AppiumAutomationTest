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

import java.net.MalformedURLException;
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

//            //to specify app for testing
//            //it can be on your computer or somewhere in cloud
//            desiredCapabilities.setCapability("app","https://cybertek-appium.s3.amazonaws.com/etsy.apk");
//            driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
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
            //
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

            //either you specify app --> //path/to/app.apk
            //or if app is already installed, you need to specify appActivity and appPackage
            //this info you can find in the internet(ex googling: android calculator app activity and app package//line 33,34), at work ask developers

            desiredCapabilities.setCapability("appPackage", "com.android.calculator2");//application's package name
            desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");//app's MainActivity i.e. LAUNCHER activity name


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


            //verify 50-35=15
            MobileElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
            getDigit(5).click();
            getDigit(0).click();
            minus.click();
            getDigit(3).click();
            getDigit(5).click();
            equals.click();

            actualResultText = result.getText();
            Assert.assertEquals("15", actualResultText);


            //close the app at the end
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //we use android phone
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            //version of android
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.1");
            //name of the device, if it is real device we need to pass UUID parameter
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");

            //derste hata vermisti bu yuzden ogretmen bunu ekledi bende hata vermemesine ragmen ekledim. appium versionla ilgili olabilir.
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

//            //to specify app for testing
//            //it can be on your computer or somewhere in cloud
//            desiredCapabilities.setCapability("app","https://cybertek-appium.s3.amazonaws.com/etsy.apk");
//            driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");

            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void realPhoneTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION,"11.1");
        //we used real device, i get this UUID number from terminal with typing "adb devices"
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"B6BUB6TOBMWO9SJB");//UUID number B6BUB6TOBMWO9SJB
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
//        //to specfiy app for testing
//        //it can be on your computer or somewhere in cloud
//        desiredCapabilities.setCapability("app","https://cybertek-appium.s3.amazonaws.com/etsy.apk");
//        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");

        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

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
        Thread.sleep(1000);



        driver.closeApp();

    }

    //CREATE A METHOD NAMED getDigit THAT IS RETURNING MOBILE ELEMENT OF THE DIGIT THAT YOU PASS AS A PARAMETER
    public MobileElement getDigit(int digit) {
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }
}
