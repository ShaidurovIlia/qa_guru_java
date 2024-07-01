package demoqa;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBox extends TestsBase {

    @Test
    void filFormTest() {
        open("/text-box");
        $(".text-center").shouldHave(text("Text Box"));
        $("#userName").setValue("Ivan");
        $("#userEmail").setValue("Ivan@mail.ru");
        $("#currentAddress").setValue("Struchkova 18");
        $("#permanentAddress").setValue("Kazanceva 2");
        $("#submit").click();
        $("#output").shouldBe(visible);

    }

}
