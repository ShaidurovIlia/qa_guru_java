package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBox {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        String userName = "Alex Egorov";

        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));
        //$("[class=main-header]").shouldHave(text("Text Box"));

        $("#userName").setValue(userName);
        //$("[id=userName]").setValue("userName");
        $("#userEmail").setValue("shaitantip@mail.com");
        $("#currentAddress").setValue("address 156");
        $("#permanentAddress").setValue("address qa");
        $("#submit").click();

        $("#output").shouldBe(visible);


        $("#output").$("#name").shouldHave(text(userName));
        $("#output #email").shouldHave(text("shaitantip@mail.com"));
    }
}
