package pages;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = getRandomValue("Male", "Female", "Other");
    String phone = String.valueOf(faker.number().numberBetween(9151111111L, 9269999999L));
    String[] dayMonthYear = getRandomDate(18, 65);
    String dateOfBirth = String.format("%s %s,%s", dayMonthYear[0], dayMonthYear[1], dayMonthYear[2]);
    String subject = getRandomValue("Math", "Chemistry", "Physics", "Computer Science", "English", "History");
    String hobbies = getRandomValue("Sports", "Reading", "Music");
    String picture = "forTest.jpg";
    String address = faker.address().streetAddress();
    String state = getRandomValue("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    String city = getCity(state);


    private String getRandomValue(String... initialValues) {
        return faker.options().option(initialValues);
    }

    public String[] getRandomDate(int minAge, int maxAge) {
        return LocalDate.now()
                .minusYears(ThreadLocalRandom.current().nextInt(minAge, maxAge + 1))
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH))
                .split(" ");

    }

    private String getCity(String state) {
        Map<String, List<String>> citiesByState = new HashMap<>();
        citiesByState.put("NCR", Arrays.asList("Delhi", "Gurgaon", "Noida"));
        citiesByState.put("Uttar Pradesh", Arrays.asList("Agra", "Lucknow", "Merrut"));
        citiesByState.put("Haryana", Arrays.asList("Karnal", "Panipat"));
        citiesByState.put("Rajasthan", Arrays.asList("Jaipur", "Jaiselmer"));

        List<String> cities = citiesByState.get(state);

        if (cities != null && !cities.isEmpty()) {
            int randomIndex = new Random().nextInt(cities.size());
            return cities.get(randomIndex);
        }
        return null;
    }
}
