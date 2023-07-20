package pages;

import com.codeborne.selenide.SelenideElement;
import pages.component.CalendarComponent;
import pages.component.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final static String TITLE_TEXT = "Student Registration Form";
    private final static String IMG_FOLDER = "img/";

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            userSubjectInput = $("#subjectsInput"),
            hobbiesWrapperChoice = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            cityDropDown = $("#state"),
            stateAndCityChoice = $("#stateCity-wrapper"),
            stateDropDown = $("#city"),
            cityWrapper = $("#stateCity-wrapper"),
            wrapperButton = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setWrapper(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPhone(String value) {
        $("#userNumber").setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setVerifyModelAppears() {
        registrationResultsModal.verifyModelAppears();
        return this;
    }

    public RegistrationPage setVerifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);
        return this;
    }

    public RegistrationPage setSubjectMatch(String value) {
        userSubjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbiesWrapper(String value) {
        hobbiesWrapperChoice.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPicture(String value) {
        pictureUpload.uploadFromClasspath(IMG_FOLDER + value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        cityDropDown.click();
        stateAndCityChoice.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCityWrapper(String value) {
        stateDropDown.click();
        cityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setSubmit() {
        wrapperButton.click();
        return this;
    }
}
