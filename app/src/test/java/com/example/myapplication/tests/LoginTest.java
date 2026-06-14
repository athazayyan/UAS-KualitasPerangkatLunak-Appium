package com.example.myapplication.tests;

import com.example.myapplication.pages.LoginPage;
import org.openqa.selenium.By; // Ditambahkan untuk penyesuaian locator di assertion
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "TC-01: Verifikasi Login Sukses dengan Kredensial Valid")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);

        // Lakukan login menggunakan akun bawaan Swag Labs yang valid
        loginPage.loginToApplication("standard_user", "secret_sauce");

        // Validasi / Assertion: Memastikan text header "PRODUCTS" muncul di dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Perbaikan: Menggunakan By.xpath untuk menghindari konflik casting di runtime TestNG
        WebElement productHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='PRODUCTS']")
        ));

        Assert.assertTrue(productHeader.isDisplayed(), "Gagal masuk ke halaman produk utama!");
    }

    @Test(priority = 2, description = "TC-02: Verifikasi Pesan Error saat Password Salah")
    public void testLoginWithInvalidPassword() {
        // Karena session reset otomatis mati atau lanjut dari test sebelumnya,
        // kita restart/relaunch aplikasi untuk memastikan kembali ke halaman login awal
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");

        LoginPage loginPage = new LoginPage(driver);

        // Uji dengan password salah (Negative Test)
        loginPage.loginToApplication("standard_user", "password_salah");

        // Validasi / Assertion: Ambil pesan error dan pastikan sesuai ekspektasi
        String expectedError = "Username and password do not match any user in this service.";
        String actualError = loginPage.getErrorMessageText();

        Assert.assertEquals(actualError, expectedError, "Pesan error tidak sesuai atau tidak muncul!");
    }
}