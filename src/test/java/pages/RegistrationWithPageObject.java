package pages;

import org.junit.jupiter.api.Test;

public class RegistrationWithPageObject extends TestBase {

    @Test
    void successfulRegistrationTest() {
        String firstName = "Alex";
        String lastName = "Egorov";
        String email = "alex@egorov.com";
        String gender = "Other";
        String phone = "1234567890";
        String birthDay = "30";
        String birthMonth = "July";
        String birthYear = "2008";
        String subject = "English";
        String hobbies = "Sports";
        String picture = "1.png";
        String address = "Some address 1";
        String state = "NCR";
        String city = "Delhi";

        registrationPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setWrapper(gender)
                .setPhone(phone)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubjectMatch(subject)
                .setHobbiesWrapper(hobbies)
                .setPicture(picture)
                .setAddress(address)
                .setState(state)
                .setCityWrapper(city)
                .setSubmit();

        registrationPage
                .setVerifyModelAppears()
                .setVerifyResult("Student Name", firstName + " " + lastName)
                .setVerifyResult("Student Email", email)
                .setVerifyResult("Gender", gender)
                .setVerifyResult("Mobile", phone)
                .setVerifyResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .setVerifyResult("Subjects", subject)
                .setVerifyResult("Hobbies", hobbies)
                .setVerifyResult("Picture", picture)
                .setVerifyResult("Address", address)
                .setVerifyResult("State and City", state + " " + city);
    }
}
