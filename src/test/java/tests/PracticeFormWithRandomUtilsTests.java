package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.RegistrationResultModal;

import static utils.RandomUtils.*;


public class PracticeFormWithRandomUtilsTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultModal registrationResultModal = new RegistrationResultModal();

    @Test
    void fillPracticeForm() {
        String[] genders = {"Male", "Female", "Other"};

        // Входные данные
        String firstName = getRandomString(6);
        String lastName = getRandomString(8);
        String email = getRandomEmail();
        String gender = getRandomItemFromArray(genders);
        String mobile = "1234567890";
        String dayOfBirthday = "18", monthOfBirthday = "September", yearOfBirthday = "1975";
        String[] subjects = {"Maths", "English", "Chemistry"};
        String[] hobbies = {"Sports", "Music"};
        String pathToPicture = "pictures/lasso.jpg";
        String address = "Scotland, City of Glasgow, Glasgow, 35";
        String state = "Uttar Pradesh";
        String city = "Merrut";

        // Для проверки результата
        String expectedStudentName = String.format("%s %s", firstName, lastName);
        String expectedEmail = String.format("%s", email);
        String expectedGender = String.format("%s", gender);
        String expectedMobile = String.format("%s", mobile);
        String expectedDateOfBirth = String.format("%s %s,%s", dayOfBirthday, monthOfBirthday, yearOfBirthday);
        String expectedSubjects = String.join(", ", subjects);
        String expectedHobbies = String.join(", ", hobbies);
        String expectedPicture = String.format("%s", pathToPicture.split("/")[1]);
        String expectedAddress = String.format("%s", address);
        String expectedStateAndCity = String.format("%s %s", state, city);

        // Заполняем форму.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(mobile)
                .setBirthday(dayOfBirthday, monthOfBirthday, yearOfBirthday)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(pathToPicture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .pressSubmit();

        // Проверка результата.
        registrationResultModal.verifyModalAppear()
                .verifyResult("Student Name", expectedStudentName)
                .verifyResult("Student Email", expectedEmail)
                .verifyResult("Gender", expectedGender)
                .verifyResult("Mobile", expectedMobile)
                .verifyResult("Date of Birth", expectedDateOfBirth)
                .verifyResult("Subjects", expectedSubjects)
                .verifyResult("Hobbies", expectedHobbies)
                .verifyResult("Picture", expectedPicture)
                .verifyResult("Address", expectedAddress)
                .verifyResult("State and City", expectedStateAndCity);
    }
}
