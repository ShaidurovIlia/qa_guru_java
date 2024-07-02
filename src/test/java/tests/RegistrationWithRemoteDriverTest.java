package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.testBaseExtended;


@Tag("remote")
public class RegistrationWithRemoteDriverTest extends testBaseExtended {

    @Test
    void successfulRegistration() {

        String mobile = "8791134414";
        String name = "Alex";
        String lastName = "Ivanov";
        String email = "Alex@mail.ru";
        String gender = "Other";
        String birthYear = "2008";
        String birthMonth = "July";
        String birthDay = "30";
        String birthDate = birthDay + " " + birthMonth + "," + birthYear;
        String subject = "Math";
        String hobbies = "Reading";
        String address = "Milutina 6";
        String img = "forTest.jpg";
        String state = "NCR";
        String city = "Delhi";

        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setUserDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubject(subject)
                .setHobbies(hobbies)
                .setPicture(img)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        registrationPage.verifyModalAppend()
                .verifyResult("Student Name", name + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobile)
                .verifyResult("Date of Birth", birthDate)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobbies)
                .verifyResult("Picture", img)
                .verifyResult("Address", address)
                .verifyResult("State and City", state + " " + city);

    }

    @Test
    void simple1() {
        Assertions.assertTrue(2 > 1);
    }

    @Test
    void simple2() {
        Assertions.assertTrue(2 > 1);
    }

    @Test
    void simple3() {
        Assertions.assertTrue(2 > 1);
    }

    @Test
    void simple4() {
        Assertions.assertFalse(2 > 1);
    }

    @Test
    void simple5() {
        Assertions.assertFalse(1 > 2);
    }
}
