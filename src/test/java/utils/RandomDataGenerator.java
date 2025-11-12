package utils;

import java.util.concurrent.ThreadLocalRandom;
import com.github.javafaker.Faker;
import dtos.registration.SecurityQuestionEnum;

public class RandomDataGenerator {

    private static final Faker faker = new Faker();

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomPassword() {
        return faker.internet().password(8, 16, true, true);
    }

    public static String randomAnswer() {
        return faker.lorem().word();
    }

    public static int randomQuestionId() {
        return ThreadLocalRandom.current().nextInt(SecurityQuestionEnum.values().length);
    }

}
