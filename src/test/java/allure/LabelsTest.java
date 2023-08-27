package allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {
//Behaviors driven development
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    @Owner("Shaidurov Ilya")
    @Test
    public void testStaticLabels() {
    }

    //Аналог, есть возможность расширения->
    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t-> t.setName("Создание Issue для авторизованного пользователя")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "Shaidurov Ilya");
        Allure.label("Severity", SeverityLevel.BLOCKER.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}
