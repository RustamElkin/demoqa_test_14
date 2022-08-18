package com.demoqa.pages;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PracticeFormData {

    static Faker faker = new Faker();

    public List<String> subjects = List.of("Maths", "Physics", "Computer Science");

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = "Male";
    public String mobile = faker.phoneNumber().subscriberNumber(10);

//        dateOfBirthData = dateToString.format(faker.date().birthday()),

   public String hobbies = "Sports";
   public String picture = "test upload.jpg";
   public String currentAddress = faker.address().streetAddress();
   public String state = "NCR";
   public String city = "Delhi";

    public static LocalDate randomDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 30).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate dateOfBirth = randomDate();
}
