package com.example.myapplication.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By; // Ditambahkan untuk tipe data locator
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    // Perbaikan: Menggunakan tipe data 'By' untuk semua locator agar tidak terjadi ClassCastException
    private By usernameField = AppiumBy.accessibilityId("test-Username");
    private By passwordField = AppiumBy.accessibilityId("test-Password");
    private By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private By errorMessage = By.xpath("//android.widget.TextView[contains(@text, 'match any user')]");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loginToApplication(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}