package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JunitSimpleTest {

    /*Комбинация для поиска по тикету
     JRARESERVER Ctr+Shift+F*/

    @BeforeEach
    void setUp() {
        open("https://www.google.com/");
    }

    // Простейший параметризованный тест  @CsvSource
    @CsvSource({
            "Allure testops, https://qameta.io",
            "Selenide, https://selenide.org"
    })

//OR !! Если нужно использовать данные большого объема @CsvFileSource
    /*@CsvFileSource(resources = "/testData.csv")*/

    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    @Tags({@Tag("BLOKER"), @Tag("UI_TEST")})
    void productSiteUrlShouldBePresentResultsOfSearchInGoogleByProductNameQuery(
            String productName,
            String productUrl
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $("#rso").shouldHave(text(productUrl));
    }

    //Если нужно передавать всего один параметр @ValueSource
    @ValueSource(
            strings = {"Allure testops", "selenide"}
    )
    @ParameterizedTest(name = "должен содержать более 5 сниппетов в выдаче на странице {0}")
    @Tags({@Tag("BLOKER"), @Tag("UI_TEST")})
    void searchResultCount(String productName
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[lang='en']").shouldHave(CollectionCondition.sizeGreaterThan(3));
    }

    @Disabled("JRARESERVER-41589")
    @DisplayName("Адрес https://selenide.org должен быть в выдаче гугла по запросу 'selenide'")
    @Test
    @Tags({@Tag("BLOKER"), @Tag("UI_TEST")})
    void selenideSitUrlShouldBePresentResultsOfSearchInGoogleBySelenideQuery() {
        $("[name=q").setValue("selenide").pressEnter();
        $("#rso").shouldHave(text("https://selenide.org"));
    }

}
