package utils;

import java.util.List;
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
        return ThreadLocalRandom.current().nextInt(1, SecurityQuestionEnum.values().length);
    }

    public static Language randomLanguage() {
        return Language.values()[ThreadLocalRandom.current().nextInt(Language.values().length)];
    }

    public static String randomCountry() {
        return faker.address().country();
    }

    public static String randomName() {
        return faker.artist().name();
    }

    public static String randomPhoneNumber() {
        return faker.number().digits(10);
    }

    public static String randomZipCode() {
        return faker.number().digits(8);
    }

    public static String randomAddress() {
        return faker.address().streetAddress();
    }

    public static String randomCity() {
        return faker.address().city();
    }

    public static String randomState() {
        return faker.address().state();
    }

    private static final List<String> TEST_CARD_NUMBERS = List.of(
            "4242424242424242",
            "4000056655665556",
            "5555555555554444",
            "5200828282828210",
            "6011111111111117"
    );

    public static String randomCardNumber() {
        return TEST_CARD_NUMBERS.get(
                ThreadLocalRandom.current().nextInt(TEST_CARD_NUMBERS.size())
        );
    }

    public static String randomMonth() {
        int month = faker.number().numberBetween(1, 13);
        return String.valueOf(month);
    }

    public static String randomYear() {
        int year = faker.number().numberBetween(2080, 2099);
        return String.valueOf(year);
    }
}
