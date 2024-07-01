package tests.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {
    @Feature("Фича описание")
    @Story("Сценарий пользователя")
    @Owner("ShaidurovIV")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "testing полезные ссылки", url = "https://testing.com")
    @DisplayName("Описание сценария теста")
    @Test
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https:github.com");
            attachment("Source", webdriver().driver().source());
        });
    }
}
