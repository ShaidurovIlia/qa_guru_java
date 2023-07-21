package selenide;

import DTO.Glossary;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParse() throws IOException {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadPdf = $("a[href='junit-user-guide-5.9.3.pdf']").download();
        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("Stefan Bechtold");
    }

    @Test
    void xlsParse() throws IOException {
        try (InputStream resourceAsStream = cl.getResourceAsStream("sample3.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1)
                    .getCell(1).getStringCellValue());
        }
    }

    @Test
    void csvParse() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("testData.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(1)[1]).contains("https://selenide.org");
        }
    }

    @Test
    void zipParse() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("test.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test.txt");
            }
        }
    }

    @Test
    void jsonParse() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("gsonsary.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("title").getAsString())
                    .isEqualTo("S");
        }
    }

    @Test
    void jsonParseDTO() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("gsonsary.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {
            Glossary jsonObject = gson.fromJson(reader, Glossary.class);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
        }
    }
}
