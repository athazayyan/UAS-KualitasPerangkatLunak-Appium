# UAS Kualitas Perangkat Lunak - Mobile Automation Testing

Aplikasi repositori ini disusun untuk memenuhi syarat kelulusan Tugas Akhir / Ujian Akhir Semester (UAS) pada mata kuliah **Kualitas Perangkat Lunak (Kelas B)**. Proyek ini mengimplementasikan pengujian otomatis (automated testing) berbasis mobile pada Android Emulator menggunakan framework Appium dan bahasa pemrograman Java.

## Identitas Mahasiswa

* **Nama:** Achmad Atha Zayyan
* **NPM:** 2308107010033
* **Kelas:** Kualitas Perangkat Lunak - B
* **Program Studi:** Informatika
* **Fakultas:** Matematika dan Ilmu Pengetahuan Alam
* **Institusi:** Universitas Syiah Kuala

---

## Arsitektur Framework (Page Object Model)

Proyek ini menerapkan design pattern **Page Object Model (POM)** untuk memisahkan secara tegas antara repositori elemen antarmuka (UI) dengan skrip skenario pengujian. Pendekatan ini menjamin kode bersifat modular, reusable, serta mudah dirawat (maintainable).

```text
app/src/test/java/com/example/myapplication/
│
├── pages/
│   └── LoginPage.java      --> Menyimpan elemen UI locator (By) & reusable actions/methods.
│
└── tests/
    ├── BaseTest.java       --> Fondasi konfigurasi driver, Android SDK hook, & Appium session.
    └── LoginTest.java      --> Skenario utama pengujian (Positive & Negative Test Cases).

```

---

## Profil Aplikasi Target

* **Nama Aplikasi:** Swag Labs Mobile App (Sauce Labs Demo App)
* **Versi APK:** 2.7.1
* **File Lokasi:** `app/app_bin/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk`
* **Karakteristik:** Aplikasi simulasi e-commerce standar industri global yang digunakan untuk kebutuhan QA. Komponen UI stabil dan dibekali dengan atribut Accessibility ID unik untuk mempermudah otomasi.

---

## Skenario Pengujian (Test Cases)

Pengujian dilakukan menggunakan framework **TestNG** yang mencakup dua skenario utama untuk menguji ketahanan dan validasi sistem autentikasi aplikasi:

1. **TC-01: Positive Test (Login Success)**
* **Tujuan:** Memverifikasi bahwa pengguna dengan kredensial valid dapat masuk ke dalam sistem dengan aman.
* **Alur:** Input username `standard_user` -> Input password `secret_sauce` -> Klik tombol Login.
* **Assertion:** Memastikan Explicit Wait berhasil mendeteksi kemunculan teks header "PRODUCTS" pada dashboard utama aplikasi.


2. **TC-02: Negative Test (Invalid Password)**
* **Tujuan:** Memvalidasi kekuatan penanganan kesalahan (error handling) sistem saat menerima input yang salah.
* **Alur:** Reset sesi aplikasi -> Input username `standard_user` -> Input password salah `password_salah` -> Klik tombol Login.
* **Assertion:** Mencocokkan kesesuaian teks peringatan yang muncul dengan pesan ekspektasi: "Username and password do not match any user in this service."



---

## Prasyarat Sistem & Dependensi (Environment)

Sebelum menjalankan pengujian, pastikan mesin Anda telah terkonfigurasi dengan komponen berikut:

### 1. Spesifikasi Komponen Lokal

* **Node.js:** Versi LTS terbaru
* **Appium Server:** v3.5.0 (Running via `npx appium`)
* **Appium Driver:** `uiautomator2@7.6.1`
* **Java Development Kit (JDK):** Versi 21
* **Android Studio & SDK Platform:** Android API Level 36 (atau versi emulator yang aktif)

### 2. Dependensi Proyek (Gradle Version Catalog)

Pustaka diatur secara tersentralisasi pada file `libs.versions.toml`:

* `io.appium:java-client:8.6.0`
* `org.testng:testng:7.8.0`
* `org.apache.commons:commons-lang3:3.12.0` (Pustaka pendukung operasi komponen AppiumBy)

---

## Cara Menjalankan Pengujian Lokal

Ikuti langkah-langkah di bawah ini secara berurutan:

### Langkah 1: Aktifkan Emulator Android

Buka Device Manager di Android Studio, kemudian jalankan Emulator Android Anda (Pastikan name ID eksekusi sesuai, default target proyek ini: `emulator-5554`).

### Langkah 2: Jalankan Appium Server dengan Environment Path

Buka Terminal/PowerShell di laptop Anda, jalankan perintah di bawah ini untuk mengunci variabel `ANDROID_HOME` secara instan dan menyalakan server:

```bash
$env:ANDROID_HOME="C:\Users\Gwirjayan\AppData\Local\Android\Sdk"; npx appium

```

Pastikan terminal menampilkan indikator aktif port: `Appium REST http interface listener started on http://0.0.0.0:4723`.

### Langkah 3: Eksekusi Test di Android Studio

1. Buka proyek di Android Studio menggunakan **Project View**.
2. Masuk ke direktori `app/src/test/java/com/example/myapplication/tests/LoginTest.java`.
3. Klik kanan pada baris nama class `public class LoginTest`, lalu pilih **Run 'LoginTest'** (ikon segitiga panah hijau).
4. Emulator akan merespons secara otomatis melakukan instalasi APK, membuka aplikasi, mengetik, dan memvalidasi seluruh skenario pengujian sampai selesai.

---

## Hasil Pengujian (Test Results)

Berdasarkan laporan akhir TestNG Suite Execution Runner pada konsol internal, proyek otomasi ini berhasil mendulang status kelayakan penuh:

```text
===============================================
Default Suite
Total tests run: 2, Passes: 2, Failures: 0, Skips: 0
===============================================
Process finished with exit code 0

```

Dengan hasil **100% Passed**, aspek kualitas perangkat lunak pada fitur autentikasi Swag Labs dinyatakan memenuhi kriteria fungsional dan lolos uji ketahanan penanganan kesalahan.
