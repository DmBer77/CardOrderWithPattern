package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryWPTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() throws InterruptedException {
        Configuration.holdBrowserOpen = true;
        var validUser = DataGenerator.Registration.generateUser("ru");
        int daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE, firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $x("//*[contains(text(),'Запланировать')]").click();
        $(".notification__content")
                .shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        TimeUnit.SECONDS.sleep(1);
        $("[data-test-id=date] input").doubleClick().sendKeys(firstMeetingDate);
        $(By.className("button")).click();
        $x("//span[contains(text(),'Перепланировать')]").click();
        TimeUnit.SECONDS.sleep(1);
        $("[data-test-id=date] input").doubleClick().sendKeys(secondMeetingDate);
        $(By.className("button")).click();
        $x("//span[contains(text(),'Перепланировать')]").click();
        TimeUnit.SECONDS.sleep(1);
        $(".notification__content")
                .shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate));
    }
}