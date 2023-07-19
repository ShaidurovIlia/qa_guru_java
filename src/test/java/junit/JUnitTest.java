package junit;

import org.junit.jupiter.api.*;

public class JUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Это метод @BeforeAll обязательно static");
    }

    @BeforeAll
    static void afterAll() {
        System.out.println("Это метод @afterAll обязательно static");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Это метод @BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Это метод @AfterEach");
    }

    @Test
    void firstTest() {
        System.out.println("Это first test");
        Assertions.assertTrue(true);
    }

    @Test
    void secondTest() {
        System.out.println("Это second test");
        Assertions.assertTrue(true);
    }
}

