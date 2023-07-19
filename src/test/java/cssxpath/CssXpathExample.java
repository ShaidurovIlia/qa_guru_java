package cssxpath;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExample {
    @Test
    void cssXpathExample() {

        //<input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email"
        $("[data-testid=email]").setValue("1");
        $(by("data-testid", "email")).setValue("1");

        //<input type="email" class="inputtext login_form_input_box" name="email" id="email"
        $("[id=email]").setValue("1");
        $("#email]").setValue("1");
        $("input#email]").setValue("1");

        //xpath
        $x("//*[@id='email']").setValue("1");
        $x("//input[@id='email']").setValue("1");

        //<input type="email" class="inputtext login_form_input_box" name="email"
        $("[name=email]").setValue("1");
        $(byName("email")).setValue("1");

        //<input type="email" class="inputtext login_form_input_box"
        $(".login_form_input_box]").setValue("1");
        $("[class= login_form_input_box]").setValue("1");
        $("input.inputtext.login_form_input_box]").setValue("1");

        //xpath
        $x("//input[@class='inputtext'][login_form_input_box]").setValue("1");

        /*
         * <div class="inputtext">
         *     <input type="email"  class="login_form_input_box">
         *  </div>
         */

        $(".inputtext .login_form_input_box").setValue("1");
        $(".inputtext").$("login_form_input_box").setValue("1");

        /*
         * <div Hello qa.quru </div>
         */

        $x("//div[text()='Hello qa.guru']");
        $(byText("Hello qa.guru"));
        $(withText("lo qa.gur"));
    }
}
