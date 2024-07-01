package tests.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {

    private static final String REPOSITORY = "eroshenkoam/tests.allure-example";
    private static final int ISSUE = 80;

    @Test
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https:github.com");

        });
        step("Ищем репозиторий" + REPOSITORY, () -> {

            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие issues с номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).shouldHave(Condition.exist);
        });
    }

}
