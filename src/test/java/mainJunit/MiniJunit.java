package mainJunit;

import org.junit.jupiter.api.Test;
import tests.DemoTestJunit;

import java.lang.reflect.Method;

public class MiniJunit {

    public static void main(String[] args) throws Exception {
        /* 1)Ищет классы с аннотацией @Test*/
        Method[] methods = DemoTestJunit.class.getDeclaredMethods();
        for (Method method : methods) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                DemoTestJunit instance = DemoTestJunit.class.getConstructor().newInstance();
                try {
                    method.invoke(instance);
                } catch (Exception e) {
                    System.out.println("Test broken!");
                    return;
                }
                System.out.println("Test passed!");
            }

        }
    }
}
