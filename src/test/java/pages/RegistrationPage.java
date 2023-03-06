package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {
    private CalendarComponent calendarComponent = new CalendarComponent();
    private RegistrationResultModal registrationResultModal = new RegistrationResultModal();

    private final String TITLE_TEXT = "Student Registration Form";
    private SelenideElement
            firstNameInput = $("input#firstName"),
            lastNameInput = $("input#lastName"),
            emailInput = $("input#userEmail"),
            genderInput = $("#genterWrapper"),
            numberInput = $("input#userNumber"),
            birthdayInput = $("input#dateOfBirthInput"),
            subjectsInput = $("input#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateClick = $("#state"),
            stateChoose = $("#stateCity-wrapper"),
            cityClick = $("#city"),
            cityChoose = $("#stateCity-wrapper"),
            buttonSubmit = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));

        // Чтобы убрать всплывающие баннеры.
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

    public RegistrationPage clearLastName(String value) {
        lastNameInput.clear();
        return this;

    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        numberInput.setValue(value);

        return this;
    }

    public RegistrationPage setBirthday(String day, String month, String year) {
        birthdayInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String[] values) {
        for (String val: values) {
            subjectsInput.setValue(val).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String[] values) {
        for (String val: values) {
            hobbiesInput.$(byText(val)).click();
        }

        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        pictureUpload.uploadFromClasspath(path);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateClick.click();
        stateChoose.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        cityClick.click();
        cityChoose.$(byText(value)).click();

        return this;
    }

    public RegistrationPage pressSubmit() {
        buttonSubmit.click();

        return this;
    }

    public RegistrationPage verifyResultsModalAppears() {
        registrationResultModal.verifyModalAppear();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultModal.verifyResult(key, value);

        return this;
    }
}
