package github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class BestContributorToSelenide {

    @Test
    void solntsevShouldBeThenContributor() {
        open("https://github.com/selenide/selenide");
        $(".BorderGrid").$(byText("Contributors")).parent().parent()
                .$$("ul li").first().hover();
        $(".Popover").shouldHave(text("Andrei Solntsev"));
    }
}
// Команда setTimeout(function() {debugger},4000); прописывается в console, ставит паузу на 4 секунды.



