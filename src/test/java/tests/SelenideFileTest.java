package tests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SelenideFileTest {

/*Если нет аттрибута href можно скачать через PROXY, но способ из за PROXY делает тесты не стабильными

    static {
        Configuration.fileDownload = FileDownloadMode.PROXY;
    }*/

    @Test
    void selenideDownloadTests() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("a[data-testid='raw-button']").download();

        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home of _JUnit 5_.");
        }
    }

    @Test
    void selenideUploadFile() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("images/forTest.jpg");
        $("div.qq-file-info").shouldHave(text("forTest.jpg"));
    }
}
