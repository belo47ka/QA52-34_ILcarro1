package utils;
import dto.UserLombok;
import net.datafaker.Faker;

public class UserFaker {
    static Faker faker = new Faker();


    public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .username(faker.internet()
                        .emailAddress())
                .password("Belkaanna47$")
                .build();
        return user;
    }
}
