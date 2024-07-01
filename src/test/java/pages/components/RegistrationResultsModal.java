package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {

    private final String TITLE = "Thanks for submitting the form";
    private final SelenideElement
            modalDialog = $(".modal-dialog"),
            modalRegistration = $("#example-modal-sizes-title-lg");

    public void verifyModalAppears() {
        modalDialog.should(appear);
        modalRegistration.shouldHave(text(TITLE));
    }

    public void verifyResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
}
