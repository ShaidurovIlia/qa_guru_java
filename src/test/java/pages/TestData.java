package pages;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = getRandomValue("Male", "Female", "Other");
    public String phone = String.valueOf(faker.number().numberBetween(9151111111L, 9269999999L));
    public String[] dayMonthYear = getRandomDate(18, 65);
    public String dateOfBirth = String.format("%s %s,%s", dayMonthYear[0], dayMonthYear[1], dayMonthYear[2]);
    public String subject = getRandomValue("Math", "Chemistry", "Physics", "Computer Science", "English", "History");
    public String hobbies = getRandomValue("Sports", "Reading", "Music");
    public String picture = "forTest.jpg";
    public String address = faker.address().streetAddress();
    public String state = getRandomValue("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = getCity(state);


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
