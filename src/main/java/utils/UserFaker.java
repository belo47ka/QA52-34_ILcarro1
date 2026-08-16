package utils;
import dto.UserLombok;
import net.datafaker.Faker;

public class UserFaker {
    static Faker faker = new Faker();


    public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .username(faker.name().firstName())
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty("base.properties","password_for_registration"))
                .build();
        return user;
    }
}
