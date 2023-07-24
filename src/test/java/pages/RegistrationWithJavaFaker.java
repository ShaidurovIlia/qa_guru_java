package pages;

import org.junit.jupiter.api.Test;

public class RegistrationWithJavaFaker extends TestBase {
    TestData td = new TestData();

    @Test
    void successfulRegistrationTest() {

        registrationPage
                .openPage()
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setEmail(td.email)
                .setWrapper(td.gender)
                .setPhone(td.phone)
                .setBirthDate(td.dayMontYear[0], td.dayMontYear[1], td.dayMontYear[2])
                .setSubjectMatch(td.subject)
                .setHobbiesWrapper(td.hobbies)
                .setPicture(td.picture)
                .setAddress(td.address)
                .setState(td.state)
                .setCityWrapper(td.city)
                .setSubmit();

        registrationPage
                .setVerifyModelAppears()
                .setVerifyResult("Student Name", td.firstName + " " + td.lastName)
                .setVerifyResult("Student Email", td.email)
                .setVerifyResult("Gender", String.valueOf(td.gender))
                .setVerifyResult("Mobile", td.phone)
                .setVerifyResult("Date of Birth", td.dateOfBirth)
                .setVerifyResult("Subjects", td.subject)
                .setVerifyResult("Hobbies", td.hobbies)
                .setVerifyResult("Picture", td.picture)
                .setVerifyResult("Address", td.address)
                .setVerifyResult("State and City", td.state + " " + td.city);
    }
}
