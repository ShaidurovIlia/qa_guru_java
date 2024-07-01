package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.Calendar;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationPage {

    private final String IMG_FOLDER = "images/";
    private final String TITLE_PAGE = "Student Registration Form";
    Calendar calendar = new Calendar();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            gender = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            dateInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateAndCityInput = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submit = $("#submit");


    public RegistrationPage openPage() {
        step("Открывваем форму", () -> {
            open("/automation-practice-form");
        });
        step("Проверяем что открыта нужная форма", () -> {
            $(".practice-form-wrapper").shouldHave(text(TITLE_PAGE));
        });
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        gender.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    public RegistrationPage setUserDateOfBirth(String day, String month, String year) {
        dateInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage verifyModalAppend() {
        registrationResultsModal.verifyModalAppears();
        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPicture(String value) {
        pictureInput.uploadFromClasspath(IMG_FOLDER + value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.click();
        stateAndCityInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityInput.click();
        stateAndCityInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage submit() {
        submit.click();
        return this;
    }
}
