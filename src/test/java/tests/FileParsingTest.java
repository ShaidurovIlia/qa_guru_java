package tests;

import com.codeborne.pdftest.PDF;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTest {

    ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadPdf = $("a[href='junit-user-guide-5.10.2.pdf']").download();
        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

    @Test
    void csvParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("testData.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("https://qameta.io");

        }
    }

    @Test
    void jsonParseTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("menu.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example menu");
            assertThat(jsonObject.get("Product").getAsJsonObject().get("value").getAsBoolean()).isTrue();
        }
    }

    @Test
    void jsonParseImprovedTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("menu.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Menu jsonObject = gson.fromJson(reader, Menu.class);
            assertThat(jsonObject.title).isEqualTo("example menu");
            assertThat(jsonObject.product.id).isEqualTo("file");
            assertThat(jsonObject.product.value).isTrue();
        }
    }
}