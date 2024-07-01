package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoTestJunit {
    @Test
    void demo() {
        System.out.println("DEMO TEST");
        Assertions.assertTrue(3 < 2);
    }
}
