package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.RegistrationResultModal;

import java.util.Locale;
import java.util.Map;

import static java.lang.String.format;
import static utils.LittleHelper.getMapBirthdayFromDate;
import static utils.RandomUtils.getRandomItemFromArray;
import static utils.RandomUtils.getRandomItemsFromArray;


@Tag("jenkins")
public class PracticeFormWithFakerTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultModal registrationResultModal = new RegistrationResultModal();
    Faker faker = new Faker(new Locale("it"));

    @Test
    void successFillPracticeForm() {
        // Данные для генерации рандомных тестовых данных.
        String[] listGenders = {"Male", "Female", "Other"},
                listHobbies = {"Sports", "Reading", "Music"},
                listSubjects = {"Maths", "English", "Chemistry", "Arts", "Civics", "Hindi", "History", "Physics"};
        var mapStateWithCity = Map.of(
                "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
                "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
                "Haryana", new String[]{"Karnal", "Panipat"},
                "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
        );

        // Генерация тестовых данных.
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = getRandomItemFromArray(listGenders);
        String phoneNumber = faker.phoneNumber().subscriberNumber(10);
        // Магия по распаковке др, полученного от faker.
        Map<String, String> mapOfBirthDay = getMapBirthdayFromDate(faker.date().birthday());
        String dayOfBirthday = mapOfBirthDay.get("day"),
                monthOfBirthday = mapOfBirthDay.get("month"),
                yearOfBirthday = mapOfBirthDay.get("year");
        String[] subjects = getRandomItemsFromArray(listSubjects);
        String[] hobbies = getRandomItemsFromArray(listHobbies);
        String pathToPicture = "pictures/lasso.jpg";
        String address = faker.address().streetAddress();
        String state = getRandomItemFromArray(mapStateWithCity.keySet().toArray(new String[0])); // Выбран случайный штат
        String city = getRandomItemFromArray(mapStateWithCity.get(state)); // Выбран случайный город штата выше

        // Заполняем форму.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(phoneNumber)
                .setBirthday(dayOfBirthday, monthOfBirthday, yearOfBirthday)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(pathToPicture)
                .setAddress(address)
                .setStateWithCity(state, city)
                .pressSubmit();

        // Проверка результата.
        registrationResultModal.verifyModalAppear()
                .verifyResult("Student Name", format("%s %s", firstName, lastName))
                .verifyResult("Student Email", format("%s", email))
                .verifyResult("Gender", format("%s", gender))
                .verifyResult("Mobile", format("%s", phoneNumber))
                .verifyResult("Date of Birth", format("%s %s,%s", dayOfBirthday, monthOfBirthday, yearOfBirthday))
                .verifyResult("Subjects", String.join(", ", subjects))
                .verifyResult("Hobbies", String.join(", ", hobbies))
                .verifyResult("Picture", format("%s", pathToPicture.split("/")[1]))
                .verifyResult("Address", format("%s", address))
                .verifyResult("State and City", format("%s %s", state, city));
    }
}
