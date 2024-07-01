package tests;

import demoqa.TestsBase;
import org.junit.jupiter.api.Test;
import pages.TestData;

public class RegistrationWithPageObjectFakerTest extends TestsBase {
    TestData td = new TestData();

    @Test
    void successfulRegistrationTest() {
        registrationPage
                .openPage()
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setEmail(td.email)
                .setGender(td.gender)
                .setUserNumber(td.phone)
                .setUserDateOfBirth(td.dayMonthYear[0], td.dayMonthYear[1], td.dayMonthYear[2])
                .setSubject(td.subject)
                .setHobbies(td.hobbies)
                .setPicture(td.picture)
                .setAddress(td.address)
                .setState(td.state)
                .setCity(td.city)
                .submit();

        registrationPage
                .verifyModalAppend()
                .verifyResult("Student Name", td.firstName + " " + td.lastName)
                .verifyResult("Student Email", td.email)
                .verifyResult("Gender", td.gender)
                .verifyResult("Mobile", td.phone)
                .verifyResult("Date of Birth", td.dateOfBirth)
                .verifyResult("Subjects", td.subject)
                .verifyResult("Hobbies", td.hobbies)
                .verifyResult("Picture", td.picture)
                .verifyResult("Address", td.address)
                .verifyResult("State and City", td.state + " " + td.city);

    }
}
