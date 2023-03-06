package tests;

import org.junit.jupiter.api.Test;

public class PracticeFormWithPageObjectsTests  extends TestBase{

    @Test
    void fillPracticeForm() {
        // Входные данные
        String currentFirstName = "Ted";
        String currentLastName = "Lasso";
        String currentEmail = "ted_lasso@example.com";
        String currentGender = "Male";
        String currentPhone = "1234567890";
        String currentDayOfBirthday = "18", currentMonthOfBirthday = "September", currentYearOfBirthday = "1975";
        String[] currentSubjects = {"Maths", "English", "Chemistry"};
        String[] currentHobbies = {"Sports", "Music"};
        String currentPathToPicture = "pictures/lasso.jpg";
        String currentAddress = "Scotland, City of Glasgow, Glasgow, 35";
        String currentState = "Uttar Pradesh";
        String currentCity = "Merrut";

        // Заполняем форму.
        registrationPage.openPage()
                            .setFirstName(currentFirstName)
                            .setLastName(currentLastName)
                            .setEmail(currentEmail)
                            .setGender(currentGender)
                            .setPhone(currentPhone)
                            .setBirthday(currentDayOfBirthday, currentMonthOfBirthday, currentYearOfBirthday)
                            .setSubjects(currentSubjects)
                            .setHobbies(currentHobbies)
                            .uploadPicture(currentPathToPicture)
                            .setAddress(currentAddress)
                            .setState(currentState)
                            .setCity(currentCity)
                        .pressSubmit();

        // Проверка результата.
        registrationPage.verifyResultsModalAppears()
                        .verifyResult("Student Name"  , "Ted Lasso")
                        .verifyResult("Student Email" , "ted_lasso@example.com")
                        .verifyResult("Gender"        , "Male")
                        .verifyResult("Mobile"        , "1234567890")
                        .verifyResult("Date of Birth" , "18 September,1975")
                        .verifyResult("Subjects"      , "Maths, English, Chemistry")
                        .verifyResult("Hobbies"       , "Sports, Music")
                        .verifyResult("Picture"       , "lasso.jpg")
                        .verifyResult("Address"       , "Scotland, City of Glasgow, Glasgow, 35")
                        .verifyResult("State and City", "Uttar Pradesh Merrut");
    }
}
