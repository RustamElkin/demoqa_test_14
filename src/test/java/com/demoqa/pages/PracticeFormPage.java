package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.data.PracticeFormData;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsModal;
import com.demoqa.pages.components.StateAndCityComponent;
import org.apache.commons.text.WordUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.demoqa.data.PracticeFormData.dateOfBirth;
import static com.demoqa.tests.StudentRegistrationFormFillTest.practiceFormPage;

public class PracticeFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsModal resultsModal = new ResultsModal();
    StateAndCityComponent stateAndCityComponent = new StateAndCityComponent();


    static SelenideElement firstName           = $("#firstName");
    static SelenideElement lastName            = $("#lastName");
    static SelenideElement email               = $("#userEmail");
    static SelenideElement gender              = $("#genterWrapper");
    static SelenideElement mobile              = $("#userNumber");
//    static static SelenideElement dateOfBirth = $("#dateOfBirthInput"),
    static SelenideElement subjects            = $("#subjectsInput");
    static SelenideElement hobbies             = $("#hobbiesWrapper");
    static SelenideElement picture             = $("#uploadPicture");
    static SelenideElement currentAddress      = $("#currentAddress");
    static SelenideElement state               = $("#react-select-3-input");
    static SelenideElement city                = $("#react-select-4-input");
    static SelenideElement submitBtn           = $("#submit");
    static SelenideElement enteredStudentName  = $(".modal-body").$(byText("Student Name")).parent().lastChild();
    static SelenideElement enteredEmail        = $(".modal-body").$(byText("Student Email")).parent().lastChild();
    static SelenideElement enteredGender       = $(".modal-body").$(byText("Gender")).parent().lastChild();
    static SelenideElement enteredMobile       = $(".modal-body").$(byText("Mobile")).parent().lastChild();
//    static SelenideElement enteredDateOfBirth = $(".modal-body").$(byText("Date of Birth")).parent().lastChild(),
    static SelenideElement enteredSubject      = $(".modal-body").$(byText("Subjects")).parent().lastChild();
    static SelenideElement enteredHobbies      = $(".modal-body").$(byText("Hobbies")).parent().lastChild();
    static SelenideElement enteredPicture      = $(".modal-body").$(byText("Picture")).parent().lastChild();
    static SelenideElement enteredAddress      = $(".modal-body").$(byText("Address")).parent().lastChild();
    static SelenideElement enteredStateAndCity = $(".modal-body").$(byText("State and City")).parent().lastChild();



    public void removeBanners() {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    public void fillFirstName(PracticeFormData data) {
        firstName.setValue(data.firstName);
    }

    public void fillLastName(PracticeFormData data) {
        lastName.setValue(data.lastName);
    }

    public void fillEmail(PracticeFormData data) {
        email.setValue(data.email);
    }

    public void fillGender(PracticeFormData data) {
        gender.find(byText(data.gender)).click();
    }

    public void fillMobile(PracticeFormData data) {
        mobile.setValue(data.mobile);
    }

    // JS-ом не получилось почистить поле, при добавлении текущая дата возвращается и добаваляется в конец
//    public void fillDateOfBirth(PracticeFormData data) {
//        executeJavaScript("document.getElementById('dateOfBirthInput').value = ''");
//        dateOfBirth.sendKeys(data.dateOfBirthData);
//        dateOfBirth.sendKeys(ENTER);
//    }

    public void fillDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
    }

    public void fillSubjects(PracticeFormData data) {
        for (String subject : data.subjects) {
            subjects.setValue(subject).pressEnter();
        }
    }

    public void fillHobbies(PracticeFormData data) {
        hobbies.scrollIntoView(false);
        hobbies.find(byText(data.hobbies)).click();
    }

    public void fillPicture(PracticeFormData data) {
        picture.uploadFile(new File("src/test/resources/" + data.picture));
    }

    public void fillCurrentAddress(PracticeFormData data) {
        currentAddress.setValue(data.currentAddress);
    }

    public void fillsStateAndCity(PracticeFormData data) {
        stateAndCityComponent.setStateAndCity(data.state, data.city);
    }

    public void fillAPFFormAndSubmit(PracticeFormData data) {
        fillFirstName(data);
        fillLastName(data);
        fillEmail(data);
        fillGender(data);
        fillMobile(data);

        fillDateOfBirth(
                dateOfBirth.getDayOfMonth() + "",
                WordUtils.capitalize(dateOfBirth.getMonth().toString().toLowerCase(Locale.ROOT)),
                dateOfBirth.getYear() + ""
        );

        fillSubjects(data);
        fillHobbies(data);
        fillPicture(data);
        fillCurrentAddress(data);
        fillsStateAndCity(data);

        submitBtn.click();

    }

    public void checkResultsTableVisible() {
        resultsModal.checkVisible();
    }
    public void checkResult(String key, String value) {
        resultsModal.checkResult(key, value);
    }
    String parseDateOfBirthForCheck = dateOfBirth.getDayOfMonth() + " " +
            WordUtils.capitalize(dateOfBirth.getMonth().toString().toLowerCase(Locale.ROOT)) + "," +
            dateOfBirth.getYear() + ""  ;

    public void checkEquals(PracticeFormData data) {
            practiceFormPage.checkResultsTableVisible();
            enteredStudentName.shouldHave(text(data.firstName + " " + data.lastName));
            enteredEmail.shouldHave(text(data.email));
            enteredGender.shouldHave(text(data.gender));
            enteredMobile.shouldHave(text(data.mobile));
            practiceFormPage.checkResult("Date of Birth", String.valueOf(parseDateOfBirthForCheck)); // формат: "30 July,2008"
            enteredSubject.shouldHave(text(Arrays.toString(new List[]{data.subjects}).
                    replace("[", "").
                    replace("]", "")));
            enteredHobbies.shouldHave(text(data.hobbies));
            enteredPicture.shouldHave(text(data.picture));
            enteredAddress.shouldHave(text(data.currentAddress));
            enteredStateAndCity.shouldHave(text(state + " " + city));
    }
}
