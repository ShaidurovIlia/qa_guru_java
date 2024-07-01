package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.components.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    static Stream<Arguments> selenideSiteShouldContainsAllButtonsForGivenLocale() {
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))

        );
    }

    //@MethodSource ищет dataProvider по названию, они должны совпадать,
    // либо нужно прописать в параметрах аннатации name =""
    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображается кнопки{1}")
    @Tag("BLOCKER")
    void selenideSiteShouldContainsAllButtonsForGivenLocale(Locale locale, List<String> buttons) {

        open("https://selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a")
                .filter(visible)
                .shouldHave(texts(buttons));
    }
}
