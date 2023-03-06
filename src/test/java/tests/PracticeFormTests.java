package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillPracticeForm() {
        open("/automation-practice-form");

        // Чтобы убрать всплывающие баннеры
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("input#firstName").setValue("Ted");
        $("input#lastName").setValue("Lasso");
        $("input#userEmail").setValue("ted_lasso@example.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("input#userNumber").setValue("1234567890");

        $("input#dateOfBirthInput").click();
        $(".react-datepicker__month-dropdown-container").$(byText("September")).click();
        $(".react-datepicker__year-dropdown-container").$(byText("1975")).click();
        $(".react-datepicker__month-container").$(byText("18")).click();

        $("input#subjectsInput").setValue("Maths").pressEnter();
        $("input#subjectsInput").setValue("English").pressEnter();
        $("input#subjectsInput").setValue("Chemistry").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("pictures/lasso.jpg");
        $("#currentAddress").setValue("Scotland, City of Glasgow, Glasgow, 35");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Merrut")).click();

        $("#submit").click();


        // Проверка результата
        $(".table-responsive").shouldHave(
                text("Ted Lasso"),
                text("ted_lasso@example.com"),
                text("Male"),
                text("1234567890"),
                text("18 September,1975"),
                text("Maths, English, Chemistry"),
                text("Sports, Music"),
                text("lasso.jpg"),
                text("Scotland, City of Glasgow, Glasgow, 35"),
                text("Uttar Pradesh Merrut")
        );
    }
}
