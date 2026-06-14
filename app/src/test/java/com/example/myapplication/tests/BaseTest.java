package com.example.myapplication.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    // Driver ini protected supaya bisa diakses langsung oleh class Test yang mewarisinya
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Inject ANDROID_HOME via Java Code
        String sdkPath = "C:\\Users\\Gwirjayan\\AppData\\Local\\Android\\Sdk";
        System.setProperty("ANDROID_HOME", sdkPath);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");
        options.setApp("C:\\Users\\Gwirjayan\\AndroidStudioProjects\\MyApplication\\app\\app_bin\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        // Menentukan Package & Activity Utama Swag Labs secara Akurat
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        options.setNoReset(false);

        // Inisialisasi Driver ke Appium Server Lokal
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        // Menutup sesi pengujian dan aplikasi secara bersih setelah seluruh test selesai
        if (driver != null) {
            driver.quit();
        }
    }
}