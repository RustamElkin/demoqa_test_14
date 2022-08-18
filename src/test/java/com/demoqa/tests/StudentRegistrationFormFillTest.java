package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.PracticeFormData;
import com.demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormFillTest {
    static PracticeFormData formData = new PracticeFormData();
    public static PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void setup() {
//        Configuration.holdBrowserOpen = true;
//        Configuration.timeout = 10000; // 10 seconds
//        Configuration.browserSize = "1920x1080";
        // поменял разрешение экрана на маке, иначе тест не проходит
        Configuration.browserSize = "1366x768";
        Configuration.browserPosition = "0x0";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillAutomationPracticeForm() {
        open("/automation-practice-form");

        practiceFormPage.removeBanners();
        practiceFormPage.fillAPFFormAndSubmit(formData);
        practiceFormPage.checkEquals(formData);
    }
}
